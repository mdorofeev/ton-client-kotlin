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

    /**
     *   #compress_zstd
     *   Compresses data using Zstandard algorithm
     *   @param uncompressed Uncompressed data.Must be encoded as base64.
     *   @param level Compression level, from 1 to 21. Where: 1 - lowest compression level (fastest compression); 21 - highest compression level (slowest compression). If level is omitted, the default compression level is used (currently 3).
     *   @return  Compressed data. Must be encoded as base64.
     */
    suspend fun compressZstd(uncompressed: String, level: Int? = null
    ): String {
        val compressed = tonClient.request<Map<String, String>>(
            "utils.compress_zstd",
            ParamsOfCompressZstd(uncompressed, level)
        )["compressed"] ?: error("Can't parse response")

        return compressed
    }

    /**
     *   #decompress_zstd
     *   Decompresses data using Zstandard algorithm
     *   @param compressed: string – Compressed data. Must be encoded as base64.
     *   @return  Decompressed data. Must be encoded as base64.
     */
    suspend fun decompressZstd(compressed: String): String {
        val decompressed = tonClient.request<Map<String, String>>(
            "utils.decompress_zstd",
            ParamsOfDecompressZstd(compressed)
        )["decompressed"] ?: error("Can't parse response")

        return decompressed
    }
}

data class ParamsOfDecompressZstd(
    val compressed: String
)

data class ParamsOfCompressZstd(
    val uncompressed: String,
    val level: Int? = null
)

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