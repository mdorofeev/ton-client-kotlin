package ee.nx01.tonclient.debot

import ee.nx01.tonclient.NetworkConfig
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientConfig
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe

class DeBotModuleTest : StringSpec({

    "Should be able init and start deboot" {
        val client = TonClient(TonClientConfig(network = NetworkConfig("main.ton.dev")))

        val response = client.debot.init(ParamsOfInit("0:038081930f6b5211ba2c9e36cb28945954c35ccd913872a4b17b2671b83f2a88")) {
            println(it)
        }

        response shouldNotBe  null

        client.debot.start(ParamsOfStart(response.debotHandle))

        Thread.sleep(3000)
    }

    "Should be able fetch" {
        val client = TonClient(TonClientConfig(network = NetworkConfig("main.ton.dev")))

        val response = client.debot.fetch(ParamsOfFetch("0:038081930f6b5211ba2c9e36cb28945954c35ccd913872a4b17b2671b83f2a88"))

        response shouldNotBe  null
    }
})
