package ee.nx01.tonclient.net

import ee.nx01.tonclient.TestConstants
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
        val client = TonClient(TestConstants.CONFIG)

        val transactionList = client.net.transactions.query(
            TransactionFilterInput(id = StringFilterInput(eq = TestConstants.TRANSACTION_ID)),
            "id balance_delta"
        )

        transactionList shouldNotBe null
        transactionList.size shouldBe 1
        transactionList[0].balance_delta shouldNotBe null
    }

    "Get transaction list from blockchain with limit and order" {
        val client = TonClient(TestConstants.CONFIG)

        val transactionList = client.net.transactions.query(
            TransactionFilterInput(id = StringFilterInput(gt = TestConstants.TRANSACTION_ID)),
            "id balance_delta", listOf(QueryOrderByInput("id", QueryOrderByDirection.Desc)), 10
        )

        transactionList shouldNotBe null
        transactionList.size shouldBe 10
        transactionList[0].balance_delta shouldNotBe null
    }

    "Wait for collection for transaction" {
        val client = TonClient(TestConstants.CONFIG)

        val transaction = client.net.transactions.waitForCollection(
            TransactionFilterInput(id = StringFilterInput(eq = TestConstants.TRANSACTION_ID)),
            "id balance_delta"
        )

        transaction shouldNotBe null
        transaction.balance_delta shouldNotBe null
    }

    "Subscribe on transactions" {
        val client = TonClient(TestConstants.CONFIG)

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