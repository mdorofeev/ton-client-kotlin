package ee.nx01.tonclient.abi

import java.math.BigInteger


data class ParamsOfEncodeAccount(
    val stateInit: StateInitSource,
    val balance: BigInteger? = null,
    val lastTransLt: BigInteger? = null,
    val lastPaid: Int? = null
)

data class ParamsOfDecodeMessageBody(
    val abi: Abi,
    val body: String,
    val isInternal: Boolean
)

data class StateInitSource(
    val type: String = "Message",
    val source: MessageSource,
    val code: String? = null,
    val data: String? = null,
    val library: String? = null,
    val tvc: String? = null,
    val publicKey: String? = null,
    val initParams: StateInitParams? = null
)

data class MessageSource(
    val type: String =  "Encoded",
    val message: String,
    val abi: Abi? = null
)

data class StateInitParams(
    val abi: Abi,
    val value: Any
)

data class ParamsOfEncodeMessageBody(
    val abi: Abi,
    val callSet: CallSet,
    val isInternal: Boolean = true,
    val signer: Signer,
    val processingTryIndex: Int? = null
)

data class ResultOfEncodeMessageBody(
    val body: String,
    val dataToSign: String? = null
)

data class ParamsOfAttachSignatureToMessageBody(
    val abi: Abi,
    val publicKey: String,
    val message: String,
    val signature: String
)

data class ResultOfAttachSignatureToMessageBody(
    val body: String
)

data class ParamsOfDecodeMessage(
    val abi: Abi,
    val message: String
)

enum class MessageBodyType {
    Input,
    Output,
    InternalOutput,
    Event
}

data class DecodedMessageBody(
    val bodyType: MessageBodyType,
    val name: String,
    val value: Map<String,Any>? = null,
    val header: FunctionHeader?
)

data class Abi(
    val type: String = "Serialized",
    val value: Any
)

data class ResultOfEncodeMessage(
    val message: String,
    val dataToSign: String? = null,
    val address: String? = null,
    val messageId: String? = null
)

/**
 * @property abi Contract ABI
 * @property address Contract address. Must be specified in case of non deploy message.
 * @property deploySet  Deploy parameters. Must be specified in case of deploy message.
 * @property callSet Function call parameters. Must be specified in non deploy message. In case of deploy message contains parameters of constructor.
 * @property signer Signing parameters.
 * @property processingTryIndex  Processing try index. Used in message processing with retries. Encoder uses the provided try index to calculate message
/// expiration time. Expiration timeouts will grow with every retry. Default value is 0.
 */
data class ParamsOfEncodeMessage(
    val abi: Abi,

    val address: String? = null,

    val deploySet: DeploySet? = null,

    val callSet: CallSet? = null,

    val signer: Signer? = null,

    val processingTryIndex: Int = 0,
)

enum class SignerType {
    Keys,
    None,
    External
}
data class Signer(val type: SignerType = SignerType.Keys, val keys: KeyPair? = null, val publicKey: String? = null) {
    companion object {
        fun none(): Signer {
            return Signer(SignerType.None)
        }
    }
}


data class KeyPair(
    val public: String,
    val secret: String,
)

/**
 * @property tvc Content of TVC file. Must be encoded with `base64`.
 * @property workchainId Target workchain for destination address. Default is `0`.
 * @property initialData List of initial values for contract's public variables.
 */
data class DeploySet(
    val tvc: String,
    val workchainId: Int? = 0,
    val initialData: Map<String, Any>? = null,
)

/**
 * @property functionName Function name.
 * @property header Function header. If an application omit some parameters required by the contract's ABI, the library will set the default values for it.
 * @property input Function input according to ABI.
 */
data class CallSet(
    val functionName: String,
    val header: FunctionHeader? = null,
    val input: Map<String, Any>?,
)

/**
 * @property expire Message expiration time in seconds.
 * @property time Message creation time in milliseconds.
 * @property pubkey Public key used to sign message. Encoded with `hex`.
 *
 */
data class FunctionHeader(
    val expire: Long?,
    val time: Long?,
    val pubkey: String?,
)