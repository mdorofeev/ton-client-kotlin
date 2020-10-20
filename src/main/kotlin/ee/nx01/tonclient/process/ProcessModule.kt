package ee.nx01.tonclient.process

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.tvm.DecodedOutput
import ee.nx01.tonclient.tvm.MessageSource

class ProcessModule(private val tonClient: TonClient) {

    suspend fun processMessage(params: ParamsOfProcessMessage): ResultOfProcessMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.process_message", params))
    }

    suspend fun sendMessage(params: ParamsOfSendMessage): ResultOfSendMessage {
        return JsonUtils.mapper.readValue(tonClient.request("processing.send_message", params))
    }

}

data class ParamsOfSendMessage(
    val message: String,
    val abi: Abi?,
    val send_events: Boolean
)

data class ResultOfSendMessage(
    val shard_block_id: String
)


data class ParamsOfProcessMessage(
    val message: MessageSource,
    val send_events: Boolean = false
)

data class ResultOfProcessMessage(
    val transaction: Any,
    val out_messages: List<Any>,
    val decoded: DecodedOutput?
)
