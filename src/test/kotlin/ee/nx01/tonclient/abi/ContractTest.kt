package ee.nx01.tonclient.abi

import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientException
import ee.nx01.tonclient.TonUtils
import ee.nx01.tonclient.boc.ParamsOfParse
import ee.nx01.tonclient.process.ParamsOfProcessMessage
import ee.nx01.tonclient.tvm.ExecutionMode
import ee.nx01.tonclient.tvm.MessageSource
import ee.nx01.tonclient.tvm.ParamsOfExecuteMessage
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal


class ContractTest : StringSpec({
    val INTEGRATION_TEST_ENABLED = false

    "Should be able create encoded message" {
        val client = TonClient()

        val params = ParamsOfEncodeMessage(
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
            ))
        )

        val response = client.abi.encodeMessage(params)

        response shouldNotBe null
    }

    "Should be able decode message" {
        val client = TonClient()

        val message = "te6ccgEBBAEA0QABRYgAIOUk2QkCZir6x9GC0jec83d6iyaPfI+1cKptIHEpXWYMAQHh9w7spN3GKgAeE8SSmvi/Q6Scw7SVwnsVRjvNxXPh1E/qDF1GTgGA/LZwnQOlAYVq/9oxrJuv4wPCZA+XfyuYB1+82TQK99ImlZxRVPdZiYGZtUtSwPFq9bOYux/+8CiygAAAXVAHUH8X41XhBMdgs2ACAWOAHdKNExvciXNvqdqKX1ybdO59hnMullzqRH0NBClYA7/AAAAAAAAAAAAAAAAAvrwgBAMAAA=="

        val abi = Abi(value = JsonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"))

        val response = client.abi.decodeMessage(ParamsOfDecodeMessage(abi, message))

        response shouldNotBe null
    }


    "Should be able parse message" {
        val client = TonClient()

        val message = "te6ccgEBBAEA0QABRYgAIOUk2QkCZir6x9GC0jec83d6iyaPfI+1cKptIHEpXWYMAQHh9w7spN3GKgAeE8SSmvi/Q6Scw7SVwnsVRjvNxXPh1E/qDF1GTgGA/LZwnQOlAYVq/9oxrJuv4wPCZA+XfyuYB1+82TQK99ImlZxRVPdZiYGZtUtSwPFq9bOYux/+8CiygAAAXVAHUH8X41XhBMdgs2ACAWOAHdKNExvciXNvqdqKX1ybdO59hnMullzqRH0NBClYA7/AAAAAAAAAAAAAAAAAvrwgBAMAAA=="

        val response = client.boc.parseMessage(ParamsOfParse(message))

        response shouldNotBe null
    }

    "Should be able execute message" {
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
            ))
        )

        val response = client.abi.encodeMessage(message)

        val account = client.net.accounts.getAccount("0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")?.boc!!

        val params = ParamsOfExecuteMessage(MessageSource(message = response.message), account = account, mode = ExecutionMode.Full)
        val response2 = client.tvm.executeMessage(params)

        response2 shouldNotBe null
    }


    "Should be error with wrong secret on run contract" {
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
                "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c2"
            ))
        )

        val response = client.abi.encodeMessage(message)

        val account = client.net.accounts.getAccount("0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")?.boc!!

        val params = ParamsOfExecuteMessage(MessageSource(message = response.message), account = account, mode = ExecutionMode.Full)

        val exception = shouldThrow<TonClientException> {
            client.tvm.executeMessage(params)
        }

        exception.tonClientError.code shouldBe 404
    }

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
            ))
        )

        val response = client.abi.encodeMessage(message)

        val params = ParamsOfProcessMessage(MessageSource(message = response.message))
        val response2 = client.processing.processMessage(params)

        response2 shouldNotBe null
    }

})