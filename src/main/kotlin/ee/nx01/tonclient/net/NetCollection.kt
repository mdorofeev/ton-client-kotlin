package ee.nx01.tonclient.net

import ee.nx01.tonclient.types.QueryOrderByInput

interface NetCollection<C, F> {
    /**
     * ## query_collection

    Queries collection data

    Queries data that satisfies the `filter` conditions,
    limits the number of returned records and orders them.
    The projection fields are limited to  `result` fields
     */
    suspend fun query(filter: F, result: String, order: List<QueryOrderByInput>? = null, limit: Int? = null): List<C>

    /**
     * ## wait_for_collection

    Returns an object that fulfills the conditions or waits for its appearance

    Triggers only once.
    If object that satisfies the `filter` conditions
    already exists - returns it immediately.
    If not - waits for insert/update of data withing the specified `timeout`,
    and returns it.
    The projection fields are limited to  `result` fields
     */
    suspend fun waitForCollection(filter: F? = null, result: String, timeout: Int? = null): C

    /**
     * ## subscribe_collection

    Creates a subscription

    Triggers for each insert/update of data
    that satisfies the `filter` conditions.
    The projection fields are limited to  `result` fields.
     */
    suspend fun subscribe(filter: F, result: String, onResult: (result: C) -> Unit): Long
}