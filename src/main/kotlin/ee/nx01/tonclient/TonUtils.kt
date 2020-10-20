package ee.nx01.tonclient

import java.math.BigDecimal

object TonUtils {
    private val TOKEN_AMOUNT = BigDecimal(1_000_000_000)

    fun convertNano(nanoToken: Long): BigDecimal {
        return BigDecimal(nanoToken).divide(TOKEN_AMOUNT)
    }

    fun convertToken(token: BigDecimal): Long {
        return token.multiply(TOKEN_AMOUNT).toLong()
    }
}