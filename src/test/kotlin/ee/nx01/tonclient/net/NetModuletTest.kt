package ee.nx01.tonclient.net

import ee.nx01.tonclient.NetworkConfig
import ee.nx01.tonclient.TestConstants
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientConfig
import ee.nx01.tonclient.types.AccountFilterInput
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.sync.Mutex

class NetModuletTest : StringSpec({

    "Subscribe on account and suspend and resume" {
        val client = TonClient()

        val mutex = Mutex(true)

        val handler = client.net.accounts.subscribe(
            AccountFilterInput(),
            "id acc_type last_paid balance"
        ) {
            it shouldNotBe null
            it.id shouldNotBe null

            if (mutex.isLocked) {
                mutex.unlock()
            }
        }

        client.net.suspend()

        client.net.resume()

        mutex.lock()

        client.unsubscribe(handler)

    }

    "Get last shard block" {
        val client = TonClient()

        val response = client.net.findLastShardBlock(ParamsOfFindLastShardBlock(TestConstants.WALLET_ADDRESS))

        response.blockId shouldNotBe null

    }

    "Query raw GraphQL request" {
        val client = TonClient()

        val response = client.net.query(ParamsOfQuery("{info{version}}", variables = null))

        response.result shouldNotBe null
    }

    "Should be able set endpoints" {
        val client = TonClient(
            TonClientConfig(
                NetworkConfig(
                    endpoints = listOf(
                        "https://net.ton.dev",
                        "https://rustnet.ton.dev"
                    )
                )
            )
        )

        val endpointSet = EndpointsSet(listOf("https://net.ton.dev", "https://rustnet.ton.dev"))

        val fetchEndpoints = client.net.fetchEndpoints().endpoints

        fetchEndpoints shouldNotBe null

        client.net.setEndpoints(endpointSet)
    }
})