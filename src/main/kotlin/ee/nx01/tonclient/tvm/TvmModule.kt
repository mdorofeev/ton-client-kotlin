package ee.nx01.tonclient.tvm

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.Abi
import ee.nx01.tonclient.abi.MessageBodyType
import java.math.BigInteger


class TvmModule(private val tonClient: TonClient) {
    suspend fun runExecutor(params: ParamsOfRunExecutor): ResultOfRunExecutor {
        return tonClient.request("tvm.run_executor", params)
    }

    suspend fun runTvm(params: ParamsOfRunExecutor): ResultOfRunTvm {
        return tonClient.request("tvm.run_tvm", params)
    }

    suspend fun executeGet(params: ParamsOfExecuteGet): ResultOfRunExecutor {
        return tonClient.request("tvm.execute_get", params)
    }
}

data class ResultOfRunTvm(
    val outMessages: List<String>,
    val decoded: DecodedOutput?,
    val account: String
)

data class ParamsOfExecuteGet(
    val account: String,
    val functionName: String,
    val input: Map<String, Any>?,
    val executionOptions: ExecutionOptions?
)

data class ResultOfExecuteGet(
    val output: Map<String, Any>
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
    val account: String? = null,
    val mode: ExecutionMode = ExecutionMode.Full,
    val executionOptions: ExecutionOptions? = null
)

data class ExecutionOptions(
    val blockchainConfig: String? = null,
    val blockTime: Long? = null,
    val blockLt: BigInteger? = null,
    val transactionLt: BigInteger? = null
)

data class MessageSource(
    val type: String = "Encoded",
    val message: String,
    val abi: Abi? = null
)


data class FunctionHeader(
    /// Message expiration time in seconds.
    val expire: Long?,

    /// Message creation time in milliseconds.
    val time: Long?,

    /// Public key used to sign message. Encoded with `hex`.
    val pubkey: String?,
)


enum class ExecutionMode {
    Full,
    TvmOnly
}
