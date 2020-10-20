package ee.nx01.tonclient.crypto

import ee.nx01.tonclient.TonClient
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe


class CryptoTest : StringSpec({
    "Created key pair should have not empty values" {
        val client = TonClient()

        val keyPair = client.crypto.ed25519Keypair()

        keyPair.public shouldNotBe null
        keyPair.secret shouldNotBe null
    }

    "get keypair from mnemonic phrase" {
        val client = TonClient()

        val HD_PATH = "m/44'/396'/0'/0/0";

        val keyPair = client.crypto.mnemonicDeriveSignKeys(
            TONMnemonicDeriveSignKeysParams(
                TONMnemonicDictionaryType.ENGLISH, TONMnemonicWordCountType.WORDS12,
                "draw motion powder someone shoot fantasy sunny recycle steel name unveil opinion", HD_PATH
            )
        )

        keyPair.secret shouldBe "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
        keyPair.public shouldBe "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca"

    }
})