package ee.nx01.tonclient.net

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.types.QueryOrderByInput

class NetModule(private val tonClient: TonClient) {

    val accounts = Accounts(this)
    val transactions = Transactions(this)
    val blocks = Blocks(this)
    val messages = Messages(this)

    suspend fun query(query: Query): String {
        return tonClient.requestString("net.query_collection", query)
    }

    suspend fun waitForCollection(params: ParamsOfWaitForCollection): String {
        return tonClient.requestString("net.wait_for_collection", params)
    }

    suspend fun subscribe(query: Query, onResult: (result: String) -> Unit): Long {
        return tonClient.subscribe("net.subscribe_collection", query, onResult)
    }

}

data class ParamsOfWaitForCollection(
    val collection: String,
    val filter: Any?,
    val result: String,
    val timeout: Int?
)

data class Query(
    val collection: String,
    val filter: Any,
    val result: String,
    val order: List<QueryOrderByInput>? = null,
    val limit: Int? = null
)
