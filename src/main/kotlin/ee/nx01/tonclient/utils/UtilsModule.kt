package ee.nx01.tonclient.utils

import ee.nx01.tonclient.TonClient
import java.lang.RuntimeException

class UtilsModule(private val tonClient: TonClient) {
    /**
    ## convert_address

    Converts address from any TON format to any TON format
     */
    suspend fun convertAddress(
        address: String,
        outputFormat: AddressStringFormat
    ): String {
        return tonClient.request<Map<String, String>>("utils.convert_address",
            mapOf("address" to address, "output_format" to outputFormat))["address"] ?: throw RuntimeException()
    }
}

data class AddressStringFormat(
    val type: AddressType,
    val url: Boolean? = null,
    val test: Boolean? = null,
    val bounce: Boolean? = null
)

enum class AddressType {
    AccountId,
    Hex,
    Base64
}