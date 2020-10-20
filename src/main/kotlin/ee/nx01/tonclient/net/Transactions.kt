package ee.nx01.tonclient.net

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.types.Transaction

class Transactions(private val net: NetModule) {

    suspend fun query(filter: String, result: String): List<Transaction> {
        val response = net.query(Query("transactions", filter, result))
        return JsonUtils.mapper.readValue<TransactionResponse>(response).result
    }
}


data class TransactionResponse(val result: List<Transaction>)