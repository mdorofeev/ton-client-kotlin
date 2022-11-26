package ee.nx01.tonclient.net

import ee.nx01.tonclient.TestConstants
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.types.MessageFilterInput
import ee.nx01.tonclient.types.StringFilterInput
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MessagesTest : StringSpec({
    "Get message from blockchain" {
        val client = TonClient(TestConstants.CONFIG)

        val filter =
            MessageFilterInput(id = StringFilterInput(eq = TestConstants.MESSAGE_ID))


        val messagesList = client.net.messages.query(
            filter,
            "id status boc"
        )

        messagesList shouldNotBe null
        messagesList.size shouldBe 1
        messagesList[0].id shouldBe TestConstants.MESSAGE_ID

    }

})