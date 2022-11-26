package ee.nx01.tonclient.debot

import ee.nx01.tonclient.TestConstants
import ee.nx01.tonclient.TonClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class DeBotModuleTest : StringSpec({

    "Should be able init and start deboot" {
        val client = TonClient(TestConstants.CONFIG_MAIN)

        val response =
            client.debot.init(ParamsOfInit("0:500547c55e7e71c9942b351f6afc2556d2528215ec5d8edac5b00a139ff46e5a")) {
                println(it)
            }

        response shouldNotBe null

        client.debot.start(ParamsOfStart(response.debotHandle))

        Thread.sleep(3000)
    }

    "Should be able fetch" {
        val client = TonClient(TestConstants.CONFIG_MAIN)

        val response =
            client.debot.fetch(ParamsOfFetch("0:500547c55e7e71c9942b351f6afc2556d2528215ec5d8edac5b00a139ff46e5a"))

        response shouldNotBe null
    }
})
