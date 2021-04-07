package ee.nx01.tonclient

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.abi.Abi
import mu.KotlinLogging
import java.io.File
import java.math.BigDecimal
import java.util.*

object TonUtils {
    private val TOKEN_AMOUNT = BigDecimal(1_000_000_000)
    private val logger = KotlinLogging.logger {}

    fun convertNano(nanoToken: Long): BigDecimal {
        return BigDecimal(nanoToken).divide(TOKEN_AMOUNT)
    }

    fun convertToken(token: BigDecimal): Long {
        return token.multiply(TOKEN_AMOUNT).toLong()
    }

    fun convertHexToToken(hex: String): BigDecimal {
        return convertNano(hex.replace("0x", "").toLong(radix = 16))
    }

    fun readAbi(abiName: String): Abi {
        val abiStream = JsonUtils::class.java.getResourceAsStream(("/contracts/$abiName"))

        abiStream.use {
            return Abi(value = JsonUtils.mapper.readValue(it))
        }
    }

    /**
     * Reads ABI from file (https://github.com/mdorofeev/ton-client-kotlin/issues/8)
     *
     * @param filePath
     * @return
     */
    fun readAbiFromFile(filePath: String): Abi? {
        try {
            if(!File(filePath).exists()) {
                logger.error("File not found: $filePath")
                return null
            }

            val abiStream = File(filePath).inputStream()

            abiStream.use {
                return Abi(value = JsonUtils.mapper.readValue(it))
            }
        } catch(t: Throwable) {
            logger.error(
                "Error while getting ABI from file\n" +
                        "File: $filePath\n" +
                        "Error message: ${t.message}\n" +
                        "Stack trace:\n" +
                        t.stackTrace.joinToString("\n")
            )
            return null
        }
    }

    fun readTvc(tvcName: String): String {
        val tvcStream = JsonUtils::class.java.getResourceAsStream(("/contracts/$tvcName"))

        tvcStream.use {
            return Base64.getEncoder().encodeToString(it.readBytes())
        }
    }

    /**
     * Reads TVC from file (https://github.com/mdorofeev/ton-client-kotlin/issues/8)
     *
     * @param filePath
     * @return
     */
    fun readTvcFromFile(filePath: String): String? {
        try {
            if(!File(filePath).exists()) {
                logger.error("File not found: $filePath")
                return null
            }

            val abiStream = File(filePath).inputStream()

            abiStream.use {
                return Base64.getEncoder().encodeToString(it.readBytes())
            }
        } catch(t: Throwable) {
            logger.error(
                "Error while getting TVC from file\n" +
                        "File: $filePath\n" +
                        "Error message: ${t.message}\n" +
                        "Stack trace:\n" +
                        t.stackTrace.joinToString("\n")
            )
            return null
        }
    }
}