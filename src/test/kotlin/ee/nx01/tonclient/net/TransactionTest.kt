package ee.nx01.tonclient.net

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.types.QueryOrderByDirection
import ee.nx01.tonclient.types.QueryOrderByInput
import ee.nx01.tonclient.types.StringFilterInput
import ee.nx01.tonclient.types.TransactionFilterInput
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.sync.Mutex

class TransactionTest : StringSpec({
    "Get transaction list from blockchain" {
        val client = TonClient()

        val transactionList = client.net.transactions.query(
            TransactionFilterInput(id = StringFilterInput(eq = "e5834711fb25932aa93c8dda283ac0f75481c70b61b9d26eced01a0a47638b17")),
            "id balance_delta"
        )

        transactionList shouldNotBe null
        transactionList.size shouldBe 1
        transactionList[0].balance_delta shouldNotBe null
    }

    "Get transaction list from blockchain with limit and order" {
        val client = TonClient()

        val transactionList = client.net.transactions.query(
            TransactionFilterInput(id = StringFilterInput(gt = "e5834711fb25932aa93c8dda283ac0f75481c70b61b9d26eced01a0a47638b17")),
            "id balance_delta", listOf(QueryOrderByInput("id", QueryOrderByDirection.Desc)), 10
        )

        transactionList shouldNotBe null
        transactionList.size shouldBe 10
        transactionList[0].balance_delta shouldNotBe null
    }

    "Wait for collection for transaction" {
        val client = TonClient()

        val transaction = client.net.transactions.waitForCollection(
            TransactionFilterInput(id = StringFilterInput(eq = "e5834711fb25932aa93c8dda283ac0f75481c70b61b9d26eced01a0a47638b17")),
            "id balance_delta"
        )

        transaction shouldNotBe null
        transaction.balance_delta shouldNotBe null
    }

    "Subscribe on transactions" {
        val client = TonClient()

        val mutex = Mutex(true)

        val handler = client.net.transactions.subscribe(
            TransactionFilterInput(),
            "id balance_delta"
        ) {
            it shouldNotBe null
            it.id shouldNotBe null

            if (mutex.isLocked) {
                mutex.unlock()
            }
        }

        mutex.lock()

        client.unsubscribe(handler)

    }


})