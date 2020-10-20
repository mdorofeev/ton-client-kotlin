package ee.nx01.tonclient

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class TonUtilsTest : StringSpec({

    "Check convert hex balance to number" {
        val result = TonUtils.convertHexToToken("0x1728e9f840")

        result shouldBe BigDecimal("99.470669888")
    }
})