package ee.nx01.tonclient

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TonClientTest : StringSpec({

    "Should be able get version" {
        val client = TonClient()
        client.version() shouldBe "1.0.0"
    }

    "Should be able create multiply clients" {
        repeat(10) {
            val client = TonClient()
            client.version()
        }
    }
})