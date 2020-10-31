package ee.nx01.tonclient

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class TonClientTest : StringSpec({

    "Should be able get version" {
        val client = TonClient()
        client.version() shouldBe "1.0.0"
    }

    "Should be able get build info" {
        val client = TonClient()
        client.buildInfo() shouldNotBe null
    }

    "Should be able get api referance" {
        val client = TonClient()
        client.getApiReference() shouldNotBe null
    }

    "Should be able create multiply clients" {
        repeat(10) {
            val client = TonClient()
            client.version()
        }
    }
})