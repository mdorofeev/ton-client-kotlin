package ee.nx01.tonclient.abi

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient


class AbiModule(private val tonClient: TonClient) {
    suspend fun encodeMessage(params: ParamsOfEncodeMessage): ResultOfEncodeMessage {
        return JsonUtils.mapper.readValue(tonClient.request("abi.encode_message", params))
    }

    suspend fun decodeMessage(params: ParamsOfDecodeMessage): DecodedMessageBody {
        return JsonUtils.mapper.readValue(tonClient.request("abi.decode_message", params))
    }
}

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
    val body_type: MessageBodyType,
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
    val data_to_sign: String? = null,
    val address: String? = null,
    val message_id: String? = null
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
    val deploy_set: DeploySet? = null,

    /// Function call parameters.
    ///
    /// Must be specified in non deploy message.
    ///
    /// In case of deploy message contains parameters of constructor.
    val call_set: CallSet? = null,

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
    val processing_try_index: Int? = 0,
)
data class Signer(val type: String = "Keys", val keys: KeyPair)

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
    val workchain_id: Int? = 0,

    /// List of initial values for contract's public variables.
    val initial_data: Map<String, Any>? = null,
)

data class CallSet(
    /// Function name.
    val function_name: String,

    /// Function header.
    ///
    /// If an application omit some parameters required by the
    /// contract's ABI, the library will set the default values for
    /// it.
    val header: FunctionHeader?,

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

