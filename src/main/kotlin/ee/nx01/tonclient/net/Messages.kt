package ee.nx01.tonclient.net

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.types.Message
import ee.nx01.tonclient.types.MessageFilterInput

class Messages(private val net: NetModule): NetCollection<Message, MessageFilterInput> {

    override suspend fun query(filter: MessageFilterInput, result: String): List<Message> {
        val response = net.query(Query("messages", filter, result))
        return JsonUtils.mapper.readValue<MessageResponse>(response).result
    }

    override suspend fun subscribe(filter: MessageFilterInput, result: String, onResult: (result: Message) -> Unit): Long {
        return net.subscribe(Query("messages", filter, result)) {
            onResult(JsonUtils.mapper.readValue<MessageSubscriptionResponse>(it).result)
        }
    }
}

data class MessageResponse(val result: List<Message>)

data class MessageSubscriptionResponse(val result: Message)