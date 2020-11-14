package ee.nx01.tonclient.net

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.types.AccountFilterInput
import ee.nx01.tonclient.types.StringFilterInput
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.bigdecimal.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import kotlinx.coroutines.sync.Mutex
import java.math.BigDecimal

class AccountTest : StringSpec({
    "Get account list from blockchain" {
        val client = TonClient()

        val accountList = client.net.accounts.query(
            AccountFilterInput(id = StringFilterInput(eq = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")),
            "id acc_type boc last_paid balance"
        )

        accountList shouldNotBe null
        accountList.size shouldBe 1
        accountList[0].accType shouldBe AccountType.ACTIVE
        accountList[0].getBalance() shouldBeGreaterThan BigDecimal.TEN
    }

    "Subscribe on account" {
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

        mutex.lock()

        client.unsubscribe(handler)

    }

    "search account with wrong id" {
        val client = TonClient()

        val account =
            client.net.accounts.getAccount("0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3x")

        account shouldBe null

        client.destroy()
    }

})