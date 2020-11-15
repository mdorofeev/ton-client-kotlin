package ee.nx01.tonclient.tvm

import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.abi.MessageBodyType
import java.math.BigInteger


data class ResultOfRunGet(
    val output: Any
)


data class ParamsOfRunTvm(
    val message: String,
    val account: String,
    val executionOptions: ExecutionOptions? = null,
    val abi: Abi? = null
)

data class ResultOfRunTvm(
    val outMessages: List<String>,
    val decoded: DecodedOutput?,
    val account: String
)

data class ParamsOfRunGet(
    val account: String,
    val functionName: String,
    val input: Map<String, Any>? = null,
    val executionOptions: ExecutionOptions? = null
)

data class ResultOfRunExecutor(
    val transaction: Any? = null,
    val outMessages: List<String>? = null,
    val decoded: DecodedOutput? = null,
    val account: Any? = null,
    val fees: TransactionFees
)

data class TransactionFees(
    val inMsgFwdFee: Long,
    val storageFee: Long,
    val gasFee: Long,
    val outMsgsFwdFee: Long,
    val totalAccountFees: Long,
    val totalOutput: Long,
)

data class DecodedOutput(
    val outMessages: List<DecodedMessageBody?>? = null,
    val output: Any? = null
)

data class DecodedMessageBody(
    val body_type: MessageBodyType,
    val name: String,
    val value: Any?,
    val header: FunctionHeader?
)

data class ParamsOfRunExecutor(
    val message: String,
    val account: AccountForExecutor? = null,
    val mode: ExecutionMode = ExecutionMode.Full,
    val executionOptions: ExecutionOptions? = null
)

data class AccountForExecutor(
    val type: AccountForExecutorType = AccountForExecutorType.Account,
    val boc: String,
    val unlimitedBalance: Boolean? = false
)

enum class AccountForExecutorType {
    None,
    Uninit,
    Account
}

data class ExecutionOptions(
    val blockchainConfig: String? = null,
    val blockTime: Long? = null,
    val blockLt: BigInteger? = null,
    val transactionLt: BigInteger? = null
)

/**
 * @property expire Message expiration time in seconds.
 * @property time Message creation time in milliseconds.
 * @property pubkey Public key used to sign message. Encoded with `hex`.
 */
data class FunctionHeader(
    val expire: Long?,
    val time: Long?,
    val pubkey: String?,
)


enum class ExecutionMode {
    Full,
    TvmOnly
}
