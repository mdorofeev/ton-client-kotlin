package ee.nx01.tonclient.process

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientError
import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.tvm.DecodedOutput
import ee.nx01.tonclient.tvm.MessageSource
import ee.nx01.tonclient.types.Message
import ee.nx01.tonclient.types.Transaction

class ProcessModule(private val tonClient: TonClient) {

    suspend fun processMessage(params: ParamsOfProcessMessage): ResultOfProcessMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.process_message", params))
    }

    suspend fun sendMessage(params: ParamsOfSendMessage): ResultOfSendMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.send_message", params))
    }

    suspend fun waitForTransaction(params: ParamsOfWaitForTransaction, onResult: ((result: ProcessingEvent) -> Unit)? = null): ResultOfProcessMessage {
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
    val shard_block_id: String,
    val send_events: Boolean = false
)

data class ParamsOfSendMessage(
    val message: String,
    val abi: Abi? = null,
    val send_events: Boolean = false
)

data class ResultOfSendMessage(
    val shard_block_id: String
)


data class ParamsOfProcessMessage(
    val message: MessageSource,
    val send_events: Boolean = false
)

data class ResultOfProcessMessage(
    val transaction: Transaction,
    val out_messages: List<Message>,
    val decoded: DecodedOutput? = null
)

data class ProcessingEvent(
    val type: ProcessingEventType,
    val shard_block_id: String? = null,
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
