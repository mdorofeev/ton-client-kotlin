package ee.nx01.tonclient.net

import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.abi.DecodedMessageBody
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

data class MessageNode(
    val id: String,
    val srcTransactionId: String? = null,
    val dstTransactionId: String? = null,
    val src: String? = null,
    val dst: String? = null,
    val value: String? = null,
    val bounce: Boolean,
    val decodedBody: DecodedMessageBody? = null
)

data class TransactionNode(
    val id: String,
    val inMsg: String,
    val outMsgs: List<String>,
    val accountAddr: String,
    val totalFees: String,
    val aborted: Boolean,
    val exitCode: Int? = null
)


data class ParamsOfQueryTransactionTree(
    val in_msg: String,
    val abiRegistry: List<Abi>? = null
)

data class ResultOfQueryTransactionTree(
    val messages: List<MessageNode>,
    val transactions: List<TransactionNode>
)