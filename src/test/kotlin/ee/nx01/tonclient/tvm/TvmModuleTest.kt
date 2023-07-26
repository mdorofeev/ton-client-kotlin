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

        val client = TonClient(TestConstants.CONFIG)

        val elector =
            client.net.accounts.getAccount("-1:3333333333333333333333333333333333333333333333333333333333333333")!!

        val response = client.tvm.runGet(ParamsOfRunGet(account = elector.boc!!, functionName = "active_election_id"))

        response shouldNotBe null
        response.output shouldNotBe null
    }



    "Should be able execute message" {
        val client = TonClient(TestConstants.CONFIG)

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
        val client = TonClient(TestConstants.CONFIG)

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



    "Should be error with wrong secret on run contract" {
        val client = TonClient(TestConstants.CONFIG)

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