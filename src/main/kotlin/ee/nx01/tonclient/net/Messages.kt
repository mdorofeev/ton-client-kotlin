package ee.nx01.tonclient.net

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.types.Message
import ee.nx01.tonclient.types.MessageFilterInput

class Messages(private val net: NetModule) {

    suspend fun query(filter: MessageFilterInput, result: String): List<Message> {
        val response = net.query(Query("messages", filter, result))
        return JsonUtils.mapper.readValue<MessageResponse>(response).result
    }
}

data class MessageResponse(val result: List<Message>)