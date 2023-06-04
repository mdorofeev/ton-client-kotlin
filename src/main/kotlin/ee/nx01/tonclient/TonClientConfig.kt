package ee.nx01.tonclient


data class TonClientConfig(
    val network: NetworkConfig = NetworkConfig(),
    val crypto: CryptoConfig? = null,
    val abi: AbiConfig? = null,
    val binding: Map<String, String> = mapOf("library" to "ton-client-kotlin", "version" to "0.0.63" )
)


data class NetworkConfig(
    val serverAddress: String? = "net.ton.dev",
    val endpoints: List<String>? = null,
    val maxReconnectTimeout: Int? = null,
    val messageRetriesCount: Int? = null,
    val messageProcessingTimeout: Int? = null,
    val waitForTimeout: Int? = null,
    val outOfSyncThreshold: Int? = null,
    val accessKey: String? = null,
    val queriesProtocol: String? = null
)

data class CryptoConfig(
    val mnemonicDictionary: Int? = null,
    val mnemonicWordCount: Int? = null,
    val hdkeyDerivationPath: String? = null,
)

data class AbiConfig(
    val workchain: Int? = null,
    val messageExpirationTimeout: Int? = null,
    val messageExpirationTimeoutGrowFactor: Int? = null
)

