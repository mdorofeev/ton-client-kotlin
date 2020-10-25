package ee.nx01.tonclient

import com.fasterxml.jackson.module.kotlin.readValue
import ee.nx01.tonclient.abi.Abi
import java.math.BigDecimal
import java.util.*

object TonUtils {
    private val TOKEN_AMOUNT = BigDecimal(1_000_000_000)

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
            return Abi(value = JsonUtils.mapper.readValue(abiStream))
        }
    }

    fun readTvc(tvcName: String): String {
        val tvcStream = JsonUtils::class.java.getResourceAsStream(("/contracts/$tvcName"))

        tvcStream.use {
            return Base64.getEncoder().encodeToString(tvcStream.readAllBytes())
        }
    }
}