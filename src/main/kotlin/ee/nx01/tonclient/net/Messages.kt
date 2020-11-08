package ee.nx01.tonclient.net

import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.types.Message
import ee.nx01.tonclient.types.MessageFilterInput
import ee.nx01.tonclient.types.QueryOrderByInput

class Messages(private val net: NetModule) : NetCollection<Message, MessageFilterInput> {

    override suspend fun query(
        filter: MessageFilterInput, result: String,
        order: List<QueryOrderByInput>?,
        limit: Int?
    ): List<Message> {
        val response = net.query(Query("messages", filter, result, order, limit))
        return JsonUtils.read<MessageResponse>(response).result
    }

    override suspend fun waitForCollection(
        filter: MessageFilterInput?,
        result: String,
        timeout: Int?
    ): Message {
        val response = net.waitForCollection(ParamsOfWaitForCollection("messages", filter, result, timeout))

        return JsonUtils.read<MessageSubscriptionResponse>(response).result
    }

    override suspend fun subscribe(
        filter: MessageFilterInput,
        result: String,
        onResult: (result: Message) -> Unit
    ): Long {
        return net.subscribe(Query("messages", filter, result)) {
            onResult(JsonUtils.read<MessageSubscriptionResponse>(it).result)
        }
    }
}

data class MessageResponse(val result: List<Message>)

data class MessageSubscriptionResponse(val result: Message)