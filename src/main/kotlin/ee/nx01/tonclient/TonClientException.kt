package ee.nx01.tonclient

import com.fasterxml.jackson.annotation.JsonCreator

class TonClientException(val tonClientError: TonClientError) :
        RuntimeException("${tonClientError.message}, Details: $tonClientError")

data class TonClientError(
    val message: String,
    val code: TonClientErrorCode,
    val data: Map<String, Any>
)


enum class TonClientErrorCode(val code: Int) {
    Unknown(0),
    // Client
    NotImplemented(1),
    InvalidHex(2),
    InvalidBase64(3),
    InvalidAddress(4),
    CallbackParamsCantBeConvertedToJson(5),
    WebsocketConnectError(6),
    WebsocketReceiveError(7),
    WebsocketSendError(8),
    HttpClientCreateError(9),
    HttpRequestCreateError(10),
    HttpRequestSendError(11),
    HttpRequestParseError(12),
    CallbackNotRegistered(13),
    NetModuleNotInit(14),
    InvalidConfig(15),
    CannotCreateRuntime(16),
    InvalidContextHandle(17),
    CannotSerializeResult(18),
    CannotSerializeError(19),
    CannotConvertJsValueToJson(20),
    CannotReceiveSpawnedResult(21),
    SetTimerError(22),
    InvalidParams(23),
    ContractsAddressConversionFailed(24),
    UnknownFunction( 25),
    // Crypto
    InvalidPublicKey(100),
    InvalidSecretKey(101),
    InvalidKey(102),
    InvalidFactorizeChallenge(106),
    InvalidBigInt(107),
    ScryptFailed(108),
    InvalidKeySize(109),
    Bip39InvalidEntropy(113),
    Bip39InvalidPhrase(114),
    Bip32InvalidKey(115),
    Bip32InvalidDerivePath(116),
    Bip39InvalidDictionary(117),
    Bip39InvalidWordCount(118),
    MnemonicGenerationFailed(119),
    MnemonicFromEntropyFailed(120),
    // BOC
    InvalidBoc(201),
    SerializationError(202),
    InappropriateBlock(203),
    MissingSourceBoc(204),
    // ABI
    RequiredAddressMissingForEncodeMessage(301),
    RequiredCallSetMissingForEncodeMessage(302),
    InvalidJson(303),
    InvalidMessage(304),
    EncodeDeployMessageFailed(305),
    EncodeRunMessageFailed(306),
    AttachSignatureFailed(307),
    InvalidTvcImage(308),
    RequiredPublicKeyMissingForFunctionHeader(309),
    InvalidSigner(310),
    // TVM
    CanNotReadTransaction(401),
    CanNotReadBlockchainConfig(402),
    TransactionAborted(403),
    ContractExecutionError(404),
    ActionPhaseFailed(405),
    AccountCodeMissing(406),
    LowBalance(407),
    AccountFrozenOrDeleted(408),
    AccountMissing(409),
    UnknownExecutionError(410),
    InvalidInputStack(411),
    InvalidAccountBoc(412),
    InvalidMessageType(413),
    InternalError(414),
    // Processing
    MessageAlreadyExpired( 501),
    MessageHasNotDestinationAddress(502),
    CanNotBuildMessageCell(503),
    FetchBlockFailed(504),
    SendMessageFailed(505),
    InvalidMessageBoc(506),
    MessageExpired(507),
    TransactionWaitTimeout(508),
    InvalidBlockReceived(509),
    CanNotCheckBlockShard(510),
    BlockNotFound(511),
    InvalidData(512),
    ExternalSignerMustNotBeUsed(513),
    // Net
    QueryFailed(601),
    SubscribeFailed(602),
    WaitForFailed(603),
    GetSubscriptionResultFailed(604),
    InvalidServerResponse(605),
    ClockOutOfSync(606),
    WaitForTimeout(607),
    GraphqlError(608);


    companion object {
        @JsonCreator @JvmStatic fun fromIntRepresentation(intValue: Int): TonClientErrorCode {
            return values().firstOrNull { it.code == intValue } ?: Unknown
        }
    }


}