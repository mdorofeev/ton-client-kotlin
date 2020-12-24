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

    /**
     *  #wait_for_collection
     *  Returns an object that fulfills the conditions or waits for its appearance
     *  Triggers only once. If object that satisfies the filter conditions already exists - returns it immediately.
     *  If not - waits for insert/update of data within the specified timeout, and returns it.
     *  The projection fields are limited to result fields
     */
    suspend fun waitForCollection(params: ParamsOfWaitForCollection): String {
        return tonClient.requestString("net.wait_for_collection", params)
    }

    /**
     * #Creates a subscription
     * Triggers for each insert/update of data that satisfies the filter conditions. The projection fields are limited to result fields.
     */
    suspend fun subscribe(query: Query, onResult: (result: String) -> Unit): Long {
        return tonClient.subscribe("net.subscribe_collection", query, onResult)
    }

    /**
     * Suspends network module to stop any network activity
     */
    suspend fun suspend() {
        tonClient.requestString("net.suspend", "")
    }

    /**
     * Resumes network module to enable network activity
     */
    suspend fun resume() {
        tonClient.requestString("net.resume", "")
    }

    /**
     * Performs DAppServer GraphQL query.
     */
    suspend fun query(query: ParamsOfQuery): ResultOfQuery {
        return tonClient.request("net.query", query)
    }

    /**
     * Returns ID of the last block in a specified account shard
     */
    suspend fun findLastShardBlock(params: ParamsOfFindLastShardBlock): ResultOfFindLastShardBlock {
        return tonClient.request("net.find_last_shard_block", params)
    }


}

data class ParamsOfQuery(
    val query: String,
    val variables: Any? = null
)

data class ResultOfQuery(
    val result: Any
)


data class ParamsOfFindLastShardBlock(
    val address: String
)

data class ResultOfFindLastShardBlock(
    val blockId: String
)

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
