package ee.nx01.tonclient.net

import ee.nx01.tonclient.TonClient
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

        val response = client.net.findLastShardBlock(ParamsOfFindLastShardBlock("0:09cc6748428b48892aa2af278a6e1eb44efac2064195e10efd76c9056e0269ab"))

        response.blockId shouldNotBe null

    }

    "Query raw GraphQL request" {
        val client = TonClient()

        val response = client.net.query(ParamsOfQuery("{info{version}}", variables = null))

        response.result shouldNotBe null
    }
})