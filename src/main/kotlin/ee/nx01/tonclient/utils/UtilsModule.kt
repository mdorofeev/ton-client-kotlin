package ee.nx01.tonclient.utils

import ee.nx01.tonclient.TonClient

class UtilsModule(private val tonClient: TonClient) {
    /**
    ## convert_address

    Converts address from any TON format to any TON format
     */
    suspend fun convertAddress(
        address: String,
        outputFormat: AddressStringFormat
    ): String {
        return tonClient.request<Map<String, String>>(
            "utils.convert_address",
            mapOf("address" to address, "output_format" to outputFormat)
        )["address"] ?: throw RuntimeException()
    }


    /**
     *   #calc_storage_fee
     *   Calculates storage fee for an account over a specified time period
     *   @param period Time period in seconds
     *   @return  Storage fee over a period of time in nanotokens
     */
    suspend fun calcStorageFee(
        address: String,
        period: Int
    ): Long {
        val feeString = tonClient.request<Map<String, String>>(
            "utils.calc_storage_fee",
            ParamsOfCalcStorageFee(address, period)
        )["fee"] ?: throw RuntimeException()

        return feeString.toLong()
    }
}

data class ParamsOfCalcStorageFee(
    val account: String,
    val period: Int
)

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