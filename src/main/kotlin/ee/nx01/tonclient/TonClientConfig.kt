package ee.nx01.tonclient


data class TonClientConfig(val network: NetworkConfig = NetworkConfig(),
                           val crypto: CryptoConfig? = null,
                           val abi: AbiConfig? = null)


data class NetworkConfig(val server_address: String = "net.ton.dev",
                         val access_key: String? = null,
                         val message_retries_count: Int? = null,
                         val message_processing_timeout: Int? = null,
                         val wait_for_timeout: Int? = null,
                         val out_of_sync_threshold: Long? = null)

data class CryptoConfig(
    val fish_param: String? = null
)

data class AbiConfig(
    val message_expiration_timeout: Int? = null,
    val message_expiration_timeout_grow_factor: Int? = null
)