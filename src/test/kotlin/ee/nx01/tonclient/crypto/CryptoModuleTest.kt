package ee.nx01.tonclient.crypto

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientErrorCode
import ee.nx01.tonclient.TonClientException
import ee.nx01.tonclient.abi.KeyPair
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe


class CryptoModuleTest : StringSpec({
    "Created key pair should have not empty values" {
        val client = TonClient()

        val keyPair = client.crypto.ed25519Keypair()

        keyPair.public shouldNotBe null
        keyPair.secret shouldNotBe null
    }

    "Should be able get mnemonic random string" {
        val client = TonClient()

        val mnemonicString = client.crypto.mnemonicFromRandom(MnemonicFromRandomParams())

        mnemonicString shouldNotBe null
        mnemonicString.split(" ").size shouldBe 12
    }

    "Should be able verify correct mnemonic string" {
        val client = TonClient()

        val result= client.crypto.mnemonicVerify(ParamsOfMnemonicVerify("summer property whisper " +
                "strategy input limb bag party saddle shoot cook bracket"))

        result shouldBe true
    }

    "Should be able verify incorrect mnemonic string" {
        val client = TonClient()

        val result= client.crypto.mnemonicVerify(ParamsOfMnemonicVerify("summer property whisper " +
                "strategy input limb bag party trattta"))

        result shouldBe false
    }

    "get keypair from mnemonic phrase" {
        val client = TonClient()

        val keyPair = client.crypto.keyPairFromSurfMnemonicPhrase("draw motion powder someone shoot fantasy " +
                "sunny recycle steel name unveil opinion")

        keyPair.secret shouldBe "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
        keyPair.public shouldBe "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca"

    }

    "Should get error from wrong mnemonic phrase" {
        val client = TonClient()

        val exception = shouldThrow<TonClientException> {
            client.crypto.keyPairFromSurfMnemonicPhrase("draw motion powder ttttttt")
        }

        exception.tonClientError.code shouldBe TonClientErrorCode.Bip39InvalidPhrase
    }


    "Sign string" {
        val client = TonClient()

        val response = client.crypto.sign(ParamsOfSign("test",
            KeyPair("7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
            "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0")))

        response.signature shouldBe "3e015aee3bf0dabf5889516a37a93718d15f2b7e5714800010d1d5cb4cfdf4bfaf15d6d1f69a7ad1cb4695faac924f674ef98234da3fbfebb6f9eb2f1504920a"
        response.signed shouldBe "PgFa7jvw2r9YiVFqN6k3GNFfK35XFIAAENHVy0z99L+vFdbR9pp60ctGlfqskk9nTvmCNNo/v+u2+esvFQSSCrXrLQ=="
    }

    "Verify signature" {
        val client = TonClient()

        val response = client.crypto.verifySignature(ParamsOfVerifySignature(
            "PgFa7jvw2r9YiVFqN6k3GNFfK35XFIAAENHVy0z99L+vFdbR9pp60ctGlfqskk9nTvmCNNo/v+u2+esvFQSSCrXrLQ==",
            "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca"))

        response.unsigned shouldBe  "test"
    }
})