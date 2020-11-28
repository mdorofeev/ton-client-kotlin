package ee.nx01.tonclient.tvm

import ee.nx01.tonclient.*
import ee.nx01.tonclient.abi.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.math.BigDecimal

class TvmModuleTest : StringSpec({

    "Should be able run get method" {

        val client = TonClient()

        val elector =
            client.net.accounts.getAccount("-1:3333333333333333333333333333333333333333333333333333333333333333")!!

        val response = client.tvm.runGet(ParamsOfRunGet(account = elector.boc!!, functionName = "active_election_id"))

        response shouldNotBe null
        response.output shouldNotBe null
    }

    "Should be able execute message" {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            callSet = CallSet(
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
            signer = Signer(
                keys = KeyPair(
                    "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                    "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
                )
            )
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount("0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")?.boc!!

        val params = ParamsOfRunExecutor(message = response.message, account = AccountForExecutor(boc = account))
        val response2 = client.tvm.runExecutor(params)

        response2 shouldNotBe null
    }


    "Should be able run tvm" {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            callSet = CallSet(
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
            signer = Signer(
                keys = KeyPair(
                    "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                    "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
                )
            )
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount("0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")?.boc!!

        val params = ParamsOfRunTvm(message = response.message, account = account)
        val response2 = client.tvm.runTvm(params)

        response2 shouldNotBe null
    }

    "Should be able run tvm depool" {
        val client = TonClient(TonClientConfig(network = NetworkConfig(serverAddress = "main.ton.dev")))

        val abi = TonUtils.readAbi("depool/DePool.abi.json")
        val message = ParamsOfEncodeMessage(
            abi = abi,
            address = "0:33518b4fc28e5f01b8e2ed24c2610add385c62827eac6e9c6926a215ab29c140",
            callSet = CallSet(
                "getParticipantInfo",
                input = mapOf(
                    "addr" to "0:4ad982cab6f55e2338bbb40463297d873ae239d637f95b5d26e713df716c5217",
                )
            ),
            signer = Signer.none()
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount("0:33518b4fc28e5f01b8e2ed24c2610add385c62827eac6e9c6926a215ab29c140")?.boc!!

        val params = ParamsOfRunTvm(message = response.message, account = account)
        val response2 = client.tvm.runTvm(params)

        val msg = client.abi.decodeMessage(ParamsOfDecodeMessage(abi = abi, message = response2.outMessages[0]))

        println(msg)

        msg.value shouldNotBe null
    }


    "Should be error with wrong secret on run contract" {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = "0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3",
            callSet = CallSet(
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
            signer = Signer(
                keys = KeyPair(
                    "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                    "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c2"
                )
            )
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount("0:1072926c848133157d63e8c1691bce79bbbd459347be47dab85536903894aeb3")?.boc!!

        val params = ParamsOfRunExecutor(message = response.message, account = AccountForExecutor(boc = account))

        val exception = shouldThrow<TonClientException> {
            client.tvm.runExecutor(params)
        }

        exception.tonClientError.code shouldBe TonClientErrorCode.InternalError
    }
})