package ee.nx01.tonclient.abi

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigInteger


data class ParamsOfEncodeAccount(
    val stateInit: StateInitSource,
    val balance: BigInteger? = null,
    val lastTransLt: BigInteger? = null,
    val lastPaid: Int? = null
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

class DecodedMessageBody(
    val bodyType: MessageBodyType,
    val name: String,
    val value: Any? = null,
    val header: FunctionHeader?
)

data class Abi(
    val type: String = "Serialized",
    @JsonProperty("value") val value: Any
)

data class ResultOfEncodeMessage(
    val message: String,
    val dataToSign: String? = null,
    val address: String? = null,
    val messageId: String? = null
)


data class ParamsOfEncodeMessage(
    /// Contract ABI.
    val abi: Abi,

    /// Contract address.
    ///
    /// Must be specified in case of non deploy message.
    val address: String? = null,

    /// Deploy parameters.
    ///
    /// Must be specified in case of deploy message.
    val deploySet: DeploySet? = null,

    /// Function call parameters.
    ///
    /// Must be specified in non deploy message.
    ///
    /// In case of deploy message contains parameters of constructor.
    val callSet: CallSet? = null,

    /// Signing parameters.
    val signer: Signer? = null,

    /// Processing try index.
    ///
    /// Used in message processing with retries.
    ///
    /// Encoder uses the provided try index to calculate message
    /// expiration time.
    ///
    /// Expiration timeouts will grow with every retry.
    ///
    /// Default value is 0.
    val processingTryIndex: Int? = 0,
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
    /// Public key. Encoded with `hex`.
    val public: String,
    /// Private key. Encoded with `hex`.
    val secret: String,
)

data class DeploySet(
    /// Content of TVC file. Must be encoded with `base64`.
    val tvc: String,

    /// Target workchain for destination address. Default is `0`.
    val workchainId: Int? = 0,

    /// List of initial values for contract's public variables.
    val initialData: Map<String, Any>? = null,
)

data class CallSet(
    /// Function name.
    val functionName: String,

    /// Function header.
    ///
    /// If an application omit some parameters required by the
    /// contract's ABI, the library will set the default values for
    /// it.
    val header: FunctionHeader? = null,

    /// Function input according to ABI.
    val input: Map<String, Any>?,
)

data class FunctionHeader(
    /// Message expiration time in seconds.
    val expire: Long?,

    /// Message creation time in milliseconds.
    val time: Long?,

    /// Public key used to sign message. Encoded with `hex`.
    val pubkey: String?,
)