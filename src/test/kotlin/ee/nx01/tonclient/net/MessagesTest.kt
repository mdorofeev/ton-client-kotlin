package ee.nx01.tonclient.net

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.types.MessageFilterInput
import ee.nx01.tonclient.types.StringFilterInput
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class MessagesTest : StringSpec({
    "Get message from blockchain" {
        val client = TonClient()

        val filter =
            MessageFilterInput(id = StringFilterInput(eq = "08e6abfe03422d16499c90b06269e5a911fc770d5ee1cfa8c6293861400d5fc8"))


        val messagesList = client.net.messages.query(filter,
            "id status boc"
        )

        messagesList shouldNotBe null
        messagesList.size shouldBe 1
        messagesList[0].id shouldBe "08e6abfe03422d16499c90b06269e5a911fc770d5ee1cfa8c6293861400d5fc8"

    }

})