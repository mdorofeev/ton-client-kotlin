package ee.nx01.tonclient

import com.fasterxml.jackson.annotation.JsonCreator
import ee.nx01.tonclient.abi.AbiModule
import ee.nx01.tonclient.boc.BocModule
import ee.nx01.tonclient.crypto.CryptoModule
import ee.nx01.tonclient.net.NetModule
import ee.nx01.tonclient.process.ProcessModule
import ee.nx01.tonclient.tvm.TvmModule
import ee.nx01.tonclient.utils.UtilsModule
import mu.KotlinLogging
import org.scijava.nativelib.NativeLoader
import ton.sdk.TONSDKJsonApi
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

class TonClient(val config: TonClientConfig = TonClientConfig()) {
    private val logger = KotlinLogging.logger {}

    private var context: Int = 0

    val net = NetModule(this)
    val abi = AbiModule(this)
    val tvm = TvmModule(this)
    val crypto = CryptoModule(this)
    val processing = ProcessModule(this)
    val boc = BocModule(this)
    val utils = UtilsModule(this)

    init {
        val result = TONSDKJsonApi.createContext(JsonUtils.write(config))
        logger.info { "Context created: $result" }
        val response = JsonUtils.read<CreateContextResponse>(result)
        context = response.result
    }


    private fun requestAsync(method: String, params: String, onResult: (result: TonClientResponse) -> Unit) {
        val requestId = Math.abs(Random.nextInt())
        logger.info { "Request to TONSDK: requestId=$requestId context=$context" }
        TONSDKJsonApi.jsonRequestAsync(context, requestId, method, params, object : Handler {
            override fun invoke(result: String, error: String, responseType: Int) {
                try {
                    onResult(TonClientResponse(result, error, TonResponseType.fromIntRepresentation(responseType)))
                } catch (e: Exception) {
                    logger.error(e.message, e)
                }
            }
        })
    }

    suspend fun subscribe(method: String, params: Any, onResult: (result: String) -> Unit): Long {
        return JsonUtils.read<SubscriptionResponse>(requestString(method, params, onResult)).handle
    }

    suspend fun unsubscribe(handle: Long) {
        requestString("net.unsubscribe", SubscriptionResponse(handle))
    }

    suspend fun version(): String {
        val response = requestString("client.version", "")
        return JsonUtils.read<Map<String, String>>(response)["version"] ?: ""
    }

    suspend fun buildInfo(): Any {
        val response = requestString("client.build_info", "")
        return JsonUtils.read<Map<String, Any>>(response)["buildInfo"] ?: ""
    }

    suspend fun getApiReference(): Any {
        val response = requestString("client.get_api_reference", "")
        return JsonUtils.read<Map<String, Any>>(response)["api"] ?: ""
    }

    private suspend fun requestToSuspend(
        method: String, params: String,
        eventCallback: ((result: String) -> Unit)? = null
    ): TonClientResponse = suspendCoroutine { cont ->

        requestAsync(method, params) {
            logger.debug { "Response: $it" }
            if (it.responseType == TonResponseType.Success || it.responseType == TonResponseType.Error) {
                cont.resume(it)
            } else if (eventCallback != null) {
                eventCallback(it.result)
            }
        }
    }

    suspend inline fun <reified T> request(
        method: String,
        params: Any,
        noinline eventCallback: ((result: String) -> Unit)? = null
    ): T {
        return JsonUtils.read(requestString(method, params, eventCallback))
    }

    suspend fun requestString(method: String, params: Any, eventCallback: ((result: String) -> Unit)? = null): String {
        val requestString = JsonUtils.write(params)

        logger.debug { "Request: $requestString" }

        val response = requestToSuspend(method, requestString, eventCallback)

        if (response.result.isNotEmpty()) {
            return response.result
        }

        throw TonClientException(JsonUtils.read(response.error))
    }

    fun destroy() {
        TONSDKJsonApi.destroyContext(context)
    }

    companion object {
        init {
            NativeLoader.loadLibrary("tonclientjni")
        }
    }

}

data class TonClientResponse(val result: String, val error: String, val responseType: TonResponseType)

enum class TonResponseType(val code: Int) {
    Success(0),
    Error(1),
    Nop(2),
    Custom(100);

    companion object {
        @JsonCreator
        @JvmStatic
        fun fromIntRepresentation(intValue: Int): TonResponseType {
            return TonResponseType.values().firstOrNull { it.code == intValue } ?: Success
        }
    }
}


data class CreateContextResponse(val result: Int)
data class SubscriptionResponse(val handle: Long)


interface Handler {
    fun invoke(result: String, error: String, responseType: Int)
}
