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

    "Should be able run get method on rust network" {

        val client = TonClient(TonClientConfig(NetworkConfig(serverAddress = "rustnet.ton.dev")))

        val elector =
            client.net.accounts.getAccount("-1:3333333333333333333333333333333333333333333333333333333333333333")!!

        val message = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("elector/Elector.abi.json"),
            address = "-1:3333333333333333333333333333333333333333333333333333333333333333",
            callSet = CallSet(
                "get",
                input = mapOf(
                )
            ),
            signer = Signer.none()
        )

        val response = client.abi.encodeMessage(message)

        val params = ParamsOfRunTvm(message = response.message, account = elector.boc!!, abi = TonUtils.readAbi("elector/Elector.abi.json"))
        val response2 = client.tvm.runTvm(params)

        response2 shouldNotBe null

    }

    "Should be able execute message" {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = TestConstants.WALLET_ADDRESS,
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
                    TestConstants.WALLET_PUBLIC,
                    TestConstants.WALLET_SECRET
                )
            )
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount(TestConstants.WALLET_ADDRESS)?.boc!!

        val params = ParamsOfRunExecutor(message = response.message, account = AccountForExecutor(boc = account))
        val response2 = client.tvm.runExecutor(params)

        response2 shouldNotBe null
    }


    "Should be able run tvm" {
        val client = TonClient()

        val message = ParamsOfEncodeMessage(
            abi = TonUtils.readAbi("setcodemultisig/SetcodeMultisigWallet.abi.json"),
            address = TestConstants.WALLET_ADDRESS,
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
                    TestConstants.WALLET_PUBLIC,
                    TestConstants.WALLET_SECRET
                )
            )
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount(TestConstants.WALLET_ADDRESS)?.boc!!

        val params = ParamsOfRunTvm(message = response.message, account = account)
        val response2 = client.tvm.runTvm(params)

        response2 shouldNotBe null
    }

    "Should be able run tvm depool" {
        val client = TonClient(TonClientConfig(network = NetworkConfig(serverAddress = "main.ton.dev")))

        val abi = TonUtils.readAbi("depool/DePool.abi.json")
        val message = ParamsOfEncodeMessage(
            abi = abi,
            address = "0:866b902a4034122b96f6312e3a00e167dd33a69e662f7b104e82d82edacb506e",
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
            client.net.accounts.getAccount("0:866b902a4034122b96f6312e3a00e167dd33a69e662f7b104e82d82edacb506e")?.boc!!

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
            address = TestConstants.WALLET_ADDRESS,
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
                    TestConstants.WALLET_PUBLIC,
                    TestConstants.WALLET_PUBLIC
                )
            )
        )

        val response = client.abi.encodeMessage(message)

        val account =
            client.net.accounts.getAccount(TestConstants.WALLET_ADDRESS)?.boc!!

        val params = ParamsOfRunExecutor(message = response.message, account = AccountForExecutor(boc = account))

        val exception = shouldThrow<TonClientException> {
            client.tvm.runExecutor(params)
        }

        exception.tonClientError.code shouldBe TonClientErrorCode.InternalError
    }
})