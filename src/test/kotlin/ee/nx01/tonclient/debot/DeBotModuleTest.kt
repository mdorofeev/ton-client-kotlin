package ee.nx01.tonclient.debot

import ee.nx01.tonclient.TonClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class DeBotModuleTest : StringSpec({

    "Should be able init and start deboot" {
        val client = TonClient()

        val response = client.debot.init(ParamsOfInit("0:f1210e078d03a64c69f4b32093e5609bbdc0513d4a0f69ae0f845de167810b51")) {
            println(it)
        }

        response shouldNotBe  null

        client.debot.start(ParamsOfStart(response.debotHandle))

        Thread.sleep(3000)
    }
})
