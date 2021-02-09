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
     * #batch_query
     *  Performs multiple queries per single fetch.
     */
    suspend fun batchQuery(query: ParamsOfBatchQuery): ResultOfQuery {
        return tonClient.request("net.batch_query", query)
    }


    /**
     * #aggregate_collection
     *   Aggregates collection data.
     *   Aggregates values from the specified fields for records that satisfies the filter conditions,
     */
    suspend fun aggregateCollection(params: ParamsOfAggregateCollection): ResultOfQuery {
        return tonClient.request("net.aggregate_collection", params)
    }

    /**
     * Returns ID of the last block in a specified account shard
     */
    suspend fun findLastShardBlock(params: ParamsOfFindLastShardBlock): ResultOfFindLastShardBlock {
        return tonClient.request("net.find_last_shard_block", params)
    }

    /**
     * fetch_endpoints
     * Requests the list of alternative endpoints from server
     */
    suspend fun fetchEndpoints(): EndpointsSet {
        return tonClient.request("net.fetch_endpoints", "")
    }

    /**
     * set_endpoints
     * Sets the list of endpoints to use on reinit
     */
    suspend fun setEndpoints(params: EndpointsSet) {
        return tonClient.request("net.set_endpoints", params)
    }


}
