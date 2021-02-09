package ee.nx01.tonclient.net

import ee.nx01.tonclient.types.QueryOrderByInput


data class ParamsOfBatchQuery(
    val operations: List<ParamsOfQuery>
)


data class EndpointsSet(
    val endpoints: List<String>
)

data class ParamsOfAggregateCollection(
    val collection: String,
    val filter: Any? = null,
    val fields: List<FieldAggregation>
)

data class FieldAggregation(
    val field: String,
    val fn: AggregationFn
)

enum class AggregationFn {
    COUNT,
    MIN,
    MAX,
    SUM,
    AVERAGE
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
