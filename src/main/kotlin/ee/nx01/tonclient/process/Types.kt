package ee.nx01.tonclient.process

import ee.nx01.tonclient.TonClientError
import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.abi.ParamsOfEncodeMessage
import ee.nx01.tonclient.tvm.DecodedOutput
import ee.nx01.tonclient.tvm.TransactionFees
import ee.nx01.tonclient.types.Transaction


data class ParamsOfWaitForTransaction(
    val abi: Abi? = null,
    val message: String,
    val shardBlockId: String,
    val sendEvents: Boolean = false
)

data class ParamsOfSendMessage(
    val message: String,
    val abi: Abi? = null,
    val sendEvents: Boolean = false
)

data class ResultOfSendMessage(
    val shardBlockId: String
)


data class ParamsOfProcessMessage(
    val messageEncodeParams: ParamsOfEncodeMessage,
    val send_events: Boolean = false
)

data class ResultOfProcessMessage(
    val transaction: Transaction,
    val outMessages: List<String>,
    val decoded: DecodedOutput? = null,
    val fees: TransactionFees
)

data class ProcessingEvent(
    val type: ProcessingEventType,
    val shardBlockId: String? = null,
    val message_id: String? = null,
    val message: String? = null,
    val result: ResultOfProcessMessage? = null,
    val error: TonClientError? = null
)

enum class ProcessingEventType {
    WillFetchFirstBlock,
    WillSend,
    DidSend,
    SendFailed,
    WillFetchNextBlock,
    FetchNextBlockFailed,
    MessageExpired,
    TransactionReceived
}

data class ParamsOfSendMessages(
    val messages: List<MessageSendingParams>,
    val monitorQueue: String? = null
)

data class ResultOfSendMessages(
    val messages: List<MessageMonitoringParams>
)

data class MessageSendingParams(
    val boc: String,
    val waitUntil: Int,
    val userData: Any? = null
)

data class ParamsOfMonitorMessages(
    val queue: String,
    val messages: List<MessageMonitoringParams>
)

data class MessageMonitoringParams(
    val message: MonitoredMessage,
    val waitUntil: Int,
    val userData: Any? = null
)

data class MonitoredMessage(
    val type: String,
    val boc: String? = null,
    val hash: String? = null,
    val address: String? = null
)