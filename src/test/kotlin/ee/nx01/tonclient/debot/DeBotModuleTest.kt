package ee.nx01.tonclient.debot

import ee.nx01.tonclient.TonClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class DeBotModuleTest : StringSpec({

    "Should be able init and start deboot" {
        val client = TonClient()

        val response = client.debot.init(ParamsOfInit("0:09403116d2d04f3d86ab2de138b390f6ec1b0bc02363dbf006953946e807051e")) {
            println(it)
        }

        response shouldNotBe  null

        client.debot.start(ParamsOfStart(response.debotHandle))

        Thread.sleep(3000)
    }

    "Should be able fetch" {
        val client = TonClient()

        val response = client.debot.fetch(ParamsOfFetch("0:09403116d2d04f3d86ab2de138b390f6ec1b0bc02363dbf006953946e807051e"))

        response shouldNotBe  null
    }
})
