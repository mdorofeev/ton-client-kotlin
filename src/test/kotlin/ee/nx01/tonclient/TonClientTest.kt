package ee.nx01.tonclient

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldStartWith

class TonClientTest : StringSpec({

    "Should be able get version" {
        val client = TonClient()
        val version = client.version()
        version shouldStartWith "1."
    }

    "Should be able get build info" {
        val client = TonClient()
        client.buildInfo() shouldNotBe null
    }

    "Should be able get api reference" {
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