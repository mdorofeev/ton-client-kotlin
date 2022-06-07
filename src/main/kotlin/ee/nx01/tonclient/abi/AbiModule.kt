package ee.nx01.tonclient.abi

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.boc.BocCacheType

/**
# Module abi

Provides message encoding and decoding according to the ABI specification.
 */
class AbiModule(private val tonClient: TonClient) {
    /**
    ## encode_message

    Encodes an ABI-compatible message

    Allows to encode deploy and function call messages,
    both signed and unsigned.

    Use cases include messages of any possible type:
    - deploy with initial function call (i.e. `constructor` or any other function that is used for some kind
    of initialization);
    - deploy without initial function call;
    - signed/unsigned + data for signing.

    `Signer` defines how the message should or shouldn't be signed:

    `Signer::None` creates an unsigned message. This may be needed in case of some public methods,
    that do not require authorization by pubkey.

    `Signer::External` takes public key and returns `data_to_sign` for later signing.
    Use `attach_signature` method with the result signature to get the signed message.

    `Signer::Keys` creates a signed message with provided key pair.
     */
    suspend fun encodeMessage(params: ParamsOfEncodeMessage): ResultOfEncodeMessage {
        return tonClient.request("abi.encode_message", params)
    }

    /**
     * ## encode_message_body
     * Encodes message body according to ABI function call.
     */
    suspend fun encodeMessageBody(params: ParamsOfEncodeMessageBody): ResultOfEncodeMessageBody {
        return tonClient.request("abi.encode_message_body", params)
    }

    /**
     * ## encode_internal_message

    Encodes an internal ABI-compatible message

    Allows to encode deploy and function call messages.

    Use cases include messages of any possible type:
    - deploy with initial function call (i.e. `constructor` or any other function that is used for some kind
    of initialization);
    - deploy without initial function call;
    - simple function call

    There is an optional public key can be provided in deploy set in order to substitute one
    in TVM file.

    Public key resolving priority:
    1. Public key from deploy set.
    2. Public key, specified in TVM file.
     */
    suspend fun encodeInternalMessage(params: ParamsOfEncodeInternalMessage): ResultOfEncodeInternalMessage {
        return tonClient.request("abi.encode_internal_message", params)
    }

    /**
     * ## decode_message_body
     * Decodes message body using provided body BOC and ABI.
     */
    suspend fun decodeMessageBody(params: ParamsOfDecodeMessageBody): DecodedMessageBody {
        return tonClient.request("abi.decode_message_body", params)
    }

    /**
     * ## decode_message
     * Decodes message body using provided message BOC and ABI.
     */
    suspend fun decodeMessage(params: ParamsOfDecodeMessage): DecodedMessageBody {
        return tonClient.request("abi.decode_message", params)
    }

    /**
    ## encode_account

    Creates account state BOC

    Creates account state provided with one of these sets of data :
    1. BOC of code, BOC of data, BOC of library
    2. TVC (string in `base64`), keys, init params
     */
    suspend fun encodeAccount(params: ParamsOfEncodeAccount) {
        return tonClient.request("abi.encode_account", params)
    }

    /**
    ## attach_signature

    Combines `hex`-encoded `signature` with `base64`-encoded `unsigned_message`. Returns signed message encoded in `base64`.
     */
    suspend fun attachSignatureToMessageBody(params: ParamsOfAttachSignatureToMessageBody): ResultOfAttachSignatureToMessageBody {
        return tonClient.request("abi.attach_signature_to_message_body", params)
    }

    /**
     * Decodes account data using provided data BOC and ABI.

        Note: this feature requires ABI 2.1 or higher.
     */
    suspend fun decodeAccountData(params: ParamsOfDecodeAccountData): ResultOfDecodeData {
        return tonClient.request("abi.decode_account_data", params)
    }

    /**
     * Updates initial account data with initial values for the contract's static variables and owner's public key.
     * This operation is applicable only for initial account data (before deploy). If the contract is already deployed,
     * its data doesn't contain this data section any more.
     */
    suspend fun updateInitialData(params: ParamsOfUpdateInitialData): ResultOfUpdateInitialData {
        return tonClient.request("abi.update_initial_data", params)
    }

    /**
     * Decodes initial values of a contract's static variables and owner's public key from account initial data This
     * operation is applicable only for initial account data (before deploy). If the contract is already deployed,
     * its data doesn't contain this data section any more.
     */
    suspend fun decodeInitialData(params: ParamsOfDecodeInitialData): ResultOfDecodeInitialData {
        return tonClient.request("abi.decode_initial_data", params)
    }

    /**
     * Encodes initial account data with initial values for the contract's static variables and owner's public key
     * into a data BOC that can be passed to encode_tvc function afterwards
     */
    suspend fun encodeInitialData(params: ParamsOfEncodeInitialData): ResultOfEncodeInitialData {
        return tonClient.request("abi.encode_initial_data", params)
    }

    /**
     * Encodes given parameters in JSON into a BOC using param types from ABI.
     */
    suspend fun encodeBoc(params: ParamsOfAbiEncodeBoc): ResultOfAbiEncodeBoc {
        return tonClient.request("abi.encode_boc", params)
    }
}
