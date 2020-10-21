package ee.nx01.tonclient.process

import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonUtils
import ee.nx01.tonclient.abi.*
import ee.nx01.tonclient.tvm.MessageSource
import ee.nx01.tonclient.types.TransactionProcessingStatusEnum
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal

class ProcessModuleTest : StringSpec({
    val INTEGRATION_TEST_ENABLED = false

    "Should be able process message".config(enabled = INTEGRATION_TEST_ENABLED) {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            Abi(value = JsonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json")),
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            call_set = CallSet(
                "submitTransaction",
                input = mapOf(
                    "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                    "value" to TonUtils.convertToken(BigDecimal(0.1)),
                    "bounce" to false,
                    "allBalance" to false,
                    "payload" to ""
                ),
                header = null
            ),
            signer = Signer(keys = KeyPair(
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
            )
            )
        )

        val response = client.abi.encodeMessage(message)

        val params = ParamsOfProcessMessage(MessageSource(message = response.message))
        val response2 = client.processing.processMessage(params)

        response2 shouldNotBe null
    }

    "Should be able send message and wait transaction".config(enabled = INTEGRATION_TEST_ENABLED) {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            Abi(value = JsonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json")),
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            call_set = CallSet(
                "submitTransaction",
                input = mapOf(
                    "dest" to "0:ee946898dee44b9b7d4ed452fae4dba773ec339974b2e75223e868214ac01dfe",
                    "value" to TonUtils.convertToken(BigDecimal(0.1)),
                    "bounce" to false,
                    "allBalance" to false,
                    "payload" to ""
                ),
                header = null
            ),
            signer = Signer(keys = KeyPair(
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
            )
            )
        )

        val response = client.abi.encodeMessage(message)

        val params = ParamsOfSendMessage(message = response.message)
        val responseSend = client.processing.sendMessage(params)

        val paramsWaitForTransaction = ParamsOfWaitForTransaction(message = response.message, shard_block_id = responseSend.shard_block_id, send_events = true)

        val responseWaitForTransaction = client.processing.waitForTransaction(paramsWaitForTransaction) {
            println(it)
        }

        responseWaitForTransaction.transaction shouldNotBe null
        responseWaitForTransaction.transaction.id shouldNotBe null
        responseWaitForTransaction.transaction.status_name shouldBe TransactionProcessingStatusEnum.Finalized

    }
})

