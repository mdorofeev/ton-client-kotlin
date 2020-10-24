package ee.nx01.tonclient


data class TonClientConfig(val network: NetworkConfig = NetworkConfig(),
                           val crypto: CryptoConfig? = null,
                           val abi: AbiConfig? = null)


data class NetworkConfig(val serverAddress: String = "net.ton.dev",
                         val accessKey: String? = null,
                         val messageRetriesCount: Int? = null,
                         val messageProcessingTimeout: Int? = null,
                         val waitForTimeout: Int? = null,
                         val outOfSyncThreshold: Long? = null)

data class CryptoConfig(
    val fishParam: String? = null
)

data class AbiConfig(
    val messageExpirationTimeout: Int? = null,
    val messageExpirationTimeoutGrowFactor: Int? = null
)