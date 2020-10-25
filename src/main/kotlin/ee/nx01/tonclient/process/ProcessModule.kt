package ee.nx01.tonclient.process

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientError
import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.abi.ParamsOfEncodeMessage
import ee.nx01.tonclient.tvm.DecodedOutput
import ee.nx01.tonclient.tvm.TransactionFees
import ee.nx01.tonclient.types.Transaction

class ProcessModule(private val tonClient: TonClient) {

    suspend fun processMessage(params: ParamsOfProcessMessage): ResultOfProcessMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.process_message", params))
    }

    suspend fun sendMessage(params: ParamsOfSendMessage): ResultOfSendMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.send_message", params))
    }

    suspend fun waitForTransaction(
        params: ParamsOfWaitForTransaction,
        onResult: ((result: ProcessingEvent) -> Unit)? = null
    ): ResultOfProcessMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.wait_for_transaction", params) {
            onResult?.invoke(
                JsonUtils.mapper.readValue(it)
            )
        })
    }

}

data class ParamsOfWaitForTransaction(
    val abi: Abi? = null,
    val message: String,
    val shardBlockId: String,
    val sendEvents: Boolean = false
)

data class ParamsOfSendMessage(
    val message: String,
    val abi: Abi? = null,
    val sendEvents: Boolean = false
)

data class ResultOfSendMessage(
    val shardBlockId: String
)


data class ParamsOfProcessMessage(
    val messageEncodeParams: ParamsOfEncodeMessage,
    val send_events: Boolean = false
)

data class ResultOfProcessMessage(
    val transaction: Transaction,
    val outMessages: List<String>,
    val decoded: DecodedOutput? = null,
    val fees: TransactionFees
)

data class ProcessingEvent(
    val type: ProcessingEventType,
    val shardBlockId: String? = null,
    val message_id: String? = null,
    val message: String? = null,
    val result: ResultOfProcessMessage? = null,
    val error: TonClientError? = null
)

enum class ProcessingEventType{
    WillFetchFirstBlock,
    WillSend,
    DidSend,
    SendFailed,
    WillFetchNextBlock,
    FetchNextBlockFailed,
    MessageExpired,
    TransactionReceived
}
