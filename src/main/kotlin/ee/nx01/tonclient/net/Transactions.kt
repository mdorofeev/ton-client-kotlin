package ee.nx01.tonclient.net

import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.types.AccountFilterInput
import ee.nx01.tonclient.types.QueryOrderByInput
import ee.nx01.tonclient.types.Transaction
import ee.nx01.tonclient.types.TransactionFilterInput

class Transactions(private val net: NetModule) : NetCollection<Transaction, TransactionFilterInput> {

    override suspend fun query(
        filter: TransactionFilterInput, result: String,
        order: List<QueryOrderByInput>?,
        limit: Int?
    ): List<Transaction> {
        val response = net.query(Query("transactions", filter, result, order, limit))
        return JsonUtils.read<TransactionResponse>(response).result
    }

    override suspend fun waitForCollection(
        filter: TransactionFilterInput?,
        result: String,
        timeout: Int?
    ): Transaction {
        val response = net.waitForCollection(ParamsOfWaitForCollection("transactions", filter, result, timeout))

        return JsonUtils.read<TransactionSubscriptionResponse>(response).result
    }

    override suspend fun subscribe(
        filter: TransactionFilterInput,
        result: String,
        onResult: (result: Transaction) -> Unit
    ): Long {
        return net.subscribe(Query("transactions", filter, result)) {
            onResult(JsonUtils.read<TransactionSubscriptionResponse>(it).result)
        }
    }
}


data class TransactionResponse(val result: List<Transaction>)

data class TransactionSubscriptionResponse(val result: Transaction)