package ee.nx01.tonclient

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith
import java.io.File
import java.math.BigDecimal

class TonUtilsTest : StringSpec({

    "Check convert hex balance to number" {
        val result = TonUtils.convertHexToToken("0x1728e9f840")

        result shouldBe BigDecimal("99.470669888")
    }

    "Should be able load abi from file" {
        val testFileStream = JsonUtils::class.java.getResourceAsStream(("/contracts/depool/DePool.abi.json"))

        val file = File.createTempFile("test", "test")

        testFileStream.use {
            file.outputStream().use {
                testFileStream.copyTo(it)
            }
        }

        val abi = TonUtils.readAbiFromFile(file.absolutePath)

        abi shouldNotBe null
    }

    "Should be able load tvc from file" {
        val testFileStream =
            JsonUtils::class.java.getResourceAsStream(("/contracts/safemultisig/SafeMultisigWallet.tvc"))

        val file = File.createTempFile("test", "test")

        testFileStream.use {
            file.outputStream().use {
                testFileStream.copyTo(it)
            }
        }

        val tvc = TonUtils.readTvcFromFile(file.absolutePath)

        tvc shouldNotBe null
        tvc shouldStartWith "te6ccgECSQEAEPQAAgE0BgEBAcACAgPPIAUDAQHeBAAD0CAAQdgAAAAAAAAAAAAAAAAAA"
    }
})