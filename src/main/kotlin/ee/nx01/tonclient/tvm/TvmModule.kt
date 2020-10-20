package ee.nx01.tonclient.tvm

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.Abi
import java.math.BigInteger


class TvmModule(private val tonClient: TonClient) {

    suspend fun executeMessage(params: ParamsOfExecuteMessage): ResultOfExecuteMessage {
        return JsonUtils.mapper.readValue(tonClient.request("tvm.execute_message", params))
    }

}

data class ResultOfExecuteMessage(
    val transaction: Any? = null,
    val out_messages: List<Any>? = null,
    val decoded: DecodedOutput? = null,
    val account: Any? = null
)

data class DecodedOutput(
    val out_messages: DecodedMessageBody? = null,
    val output: Any? = null
)

data class DecodedMessageBody(
    val message_type: String,
    val name: String,
    val value: Any,
    val header: FunctionHeader?
)

data class ParamsOfExecuteMessage(
    val message: MessageSource,
    val account: String,
    val mode: ExecutionMode,
    val execution_options: ExecutionOptions? = null
)

data class ExecutionOptions(
    val blockchain_config: String? = null,
    val block_time: Long? = null,
    val block_lt: BigInteger? = null,
    val transaction_lt: BigInteger? = null
)

data class MessageSource(
    val type: String = "Encoded",
    val message: String,
    val abi: Abi? = null
)


data class FunctionHeader(
    /// Message expiration time in seconds.
    val expire: Long?,

    /// Message creation time in milliseconds.
    val time: Long?,

    /// Public key used to sign message. Encoded with `hex`.
    val pubkey: String?,
)


enum class ExecutionMode {
    Full,
    TvmOnly
}
