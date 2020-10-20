package ee.nx01.tonclient


data class TonClientConfig(val network: NetworkConfig = NetworkConfig())


data class NetworkConfig(val server_address: String = "net.ton.dev", val access_key: String? = null)