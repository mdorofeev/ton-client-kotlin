package ee.nx01.tonclient.crypto

import ee.nx01.tonclient.TestConstants
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.TonClientErrorCode
import ee.nx01.tonclient.TonClientException
import ee.nx01.tonclient.abi.KeyPair
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import java.util.*


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

        val result = client.crypto.mnemonicVerify(
            ParamsOfMnemonicVerify(
                TestConstants.WALLET_PHRASE
            )
        )

        result shouldBe true
    }

    "Should be able verify incorrect mnemonic string" {
        val client = TonClient()

        val result = client.crypto.mnemonicVerify(
            ParamsOfMnemonicVerify(
                "summer property whisper " +
                        "strategy input limb bag party trattta"
            )
        )

        result shouldBe false
    }

    "Should get keypair from mnemonic phrase" {
        val client = TonClient()

        val keyPair = client.crypto.keyPairFromSurfMnemonicPhrase(
            TestConstants.WALLET_PHRASE
        )

        keyPair.secret shouldBe TestConstants.WALLET_SECRET
        keyPair.public shouldBe TestConstants.WALLET_PUBLIC

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

        val response = client.crypto.sign(
            ParamsOfSign(
                "test",
                KeyPair(
                    "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca",
                    "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"
                )
            )
        )

        response.signature shouldBe "3e015aee3bf0dabf5889516a37a93718d15f2b7e5714800010d1d5cb4cfdf4bfaf15d6d1f69a7ad1cb4695faac924f674ef98234da3fbfebb6f9eb2f1504920a"
        response.signed shouldBe "PgFa7jvw2r9YiVFqN6k3GNFfK35XFIAAENHVy0z99L+vFdbR9pp60ctGlfqskk9nTvmCNNo/v+u2+esvFQSSCrXrLQ=="
    }

    "Verify signature" {
        val client = TonClient()

        val response = client.crypto.verifySignature(
            ParamsOfVerifySignature(
                "PgFa7jvw2r9YiVFqN6k3GNFfK35XFIAAENHVy0z99L+vFdbR9pp60ctGlfqskk9nTvmCNNo/v+u2+esvFQSSCrXrLQ==",
                "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca"
            )
        )

        response.unsigned shouldBe "test"
    }

    "Get extended master private key" {
        val client = TonClient()

        val response = client.crypto.hdkeyXprvFromMnemonic(
            ParamsOfHDKeyXPrvFromMnemonic(
                "draw motion powder someone shoot fantasy " +
                        "sunny recycle steel name unveil opinion"
            )
        )

        response.xprv shouldNotBe null
    }

    "Should returns extended private key derived from the specified extended private key and child index" {
        val client = TonClient()

        val response = client.crypto.hdkeyDeriveFromXprv(
            ParamsOfHDKeyDeriveFromXPrv(
                "xprv9s21ZrQH143K3P9pvcyJfLYy32sscGKMGse2T1Ujv6mxwnN4VFAbZrv5YksKxqLt6jd2dbkd6EWGifoRwnXbjaCjBtXqjqaBzL1TkzVMDex",
                1,
                false
            )
        )

        response.xprv shouldBe "xprv9upEqz3JqSktfJ4Quj17Dpi5fYguFXsfBCLX7JPhaFv7rF8zvBhYkSVX5Mwe2FK8ngutE9ou6EBFFdsELsqG6VKUXeEdRMF2YRcX1A9Qkd2"
    }

    "Should derives the exented private key from the specified key and path" {
        val client = TonClient()

        val response = client.crypto.hdkeyDeriveFromXprvPath(
            ParamsOfHDKeyDeriveFromXPrvPath(
                "xprv9s21ZrQH143K3P9pvcyJfLYy32sscGKMGse2T1Ujv6mxwnN4VFAbZrv5YksKxqLt6jd2dbkd6EWGifoRwnXbjaCjBtXqjqaBzL1TkzVMDex",
                CryptoModule.HD_PATH
            )
        )

        response.xprv shouldBe "xprvA3rCvXp37apThYKCF3SQKDfQXH3mZKv2vo1z1tGG4kf5VAfCM6AJa3eg1XEJRbZ4jTjCUrNXzMv8ihqSrv928Uz1DautEjQsPQ6CioSbRzY"

    }

    "Should be able extracts the private key from the serialized extended private key" {
        val client = TonClient()

        val response = client.crypto.hdkeySecretFromXprv(
            ParamsOfHDKeySecretFromXPrv("xprvA3rCvXp37apThYKCF3SQKDfQXH3mZKv2vo1z1tGG4kf5VAfCM6AJa3eg1XEJRbZ4jTjCUrNXzMv8ihqSrv928Uz1DautEjQsPQ6CioSbRzY")
        )

        response.secret shouldBe "db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0"

    }

    "Should be able extracts the public key from the serialized extended private key" {
        val client = TonClient()

        val response = client.crypto.hdkeyPublicFromXprv(
            ParamsOfHDKeyPublicFromXPrv("xprvA3rCvXp37apThYKCF3SQKDfQXH3mZKv2vo1z1tGG4kf5VAfCM6AJa3eg1XEJRbZ4jTjCUrNXzMv8ihqSrv928Uz1DautEjQsPQ6CioSbRzY")
        )

        response.public shouldBe "038919503f8e39a4ff0fc676870537e6b1481d67829582aa5a14914fbc251a55f0"

    }

    "Should be able get key by scrypt" {
        val client = TonClient()

        val response = client.crypto.scrypt(ParamsOfScrypt("test", "salt"))
        response shouldBe "363edc6ba27fdbb2fb911fd933ca317625d857e72ccf3a83e80ae778f21c7285"

    }

    "Should be able get SHA256 hash" {
        val client = TonClient()

        val response = client.crypto.sha256("test")
        response shouldBe "6617aa88a72e6b526b88cbceda388a7b52a0e856148a12d9b8429cd2a53a3ea4"

    }

    "Should be able get SHA512 hash" {
        val client = TonClient()

        val response = client.crypto.sha512("test")
        response shouldBe "2005a1f1c71118e3b53b8be55c27b67e28bd81f84bf8462cf6d02288c107ccde8c2dccd41cea98b53941b15384c8397c2e56a4e0e7ef177c6562d8eeb9b2d0b6"

    }

    "Should be able get TON src16 hash" {
        val client = TonClient()

        val response = client.crypto.tonCrc16("test")
        response shouldBe 11400

    }


    "Should be able generate random bytes" {
        val client = TonClient()

        val response = client.crypto.generateRandomBytes(32)
        response.size shouldBe 32

    }

    "Should be able convert public key to ton format" {
        val client = TonClient()

        val response =
            client.crypto.convertPublicKeyToTonSafeFormat("7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca")
        response shouldBe "PuZ-82TQK99ImlZxRVPdZiYGZtUtSwPFq9bOYux_-8Ciyvr8"

    }

    "Should be able factorize" {
        val client = TonClient()

        val response = client.crypto.factorize("17ED48941A08F981")
        response[0] shouldBe "494C553B"
        response[1] shouldBe "53911073"

    }

    "Should be able call modularPower" {
        val client = TonClient()

        val response = client.crypto.modularPower(base = "0123456789ABCDEF", exponent = "0123", modulus = "01234567")
        response shouldBe "63bfdf"

    }

    "Should be able call crypto.nacl_sign_keypair_from_secret_key" {
        val client = TonClient()

        val response =
            client.crypto.naclSignKeypairFromSecretKey("db5da80d3bdeb607d17cf29d1c68489b5071637b3a0d8d747b7ad6ce7e89e5c0")
        response.public shouldBe "7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca"

    }

    "Should be able call crypto.nacl_sign" {
        val client = TonClient()

        val response = client.crypto.naclSign(
            "test",
            "56b6a77093d6fdf14e593f36275d872d75de5b341942376b2a08759f3cbae78f1869b7ef29d58026217e9cf163cbfbd0de889bdf1bf4daebf5433a312f5b8d6e"
        )
        response shouldBe "iqgAXl0PpQ1kZ1FPQmOPHj377RXJzEnXZW1aklWin1NqKMbpDJiMNiskpOQl23oM3sIq8yFv8PedCZGKA3ZtBrXrLQ=="

    }

    "Should be able call crypto.nacl_sign_open" {
        val client = TonClient()

        val response = client.crypto.naclSignOpen(
            "iqgAXl0PpQ1kZ1FPQmOPHj377RXJzEnXZW1aklWin1NqKMbpDJiMNiskpOQl23oM3sIq8yFv8PedCZGKA3ZtBrXrLQ==",
            "1869b7ef29d58026217e9cf163cbfbd0de889bdf1bf4daebf5433a312f5b8d6e"
        )
        response shouldBe "test"

    }

    "Should be able call crypto.nacl_sign_detached" {
        val client = TonClient()

        val response = client.crypto.naclSignDetached(
            "test",
            "56b6a77093d6fdf14e593f36275d872d75de5b341942376b2a08759f3cbae78f1869b7ef29d58026217e9cf163cbfbd0de889bdf1bf4daebf5433a312f5b8d6e"
        )
        response shouldBe "8aa8005e5d0fa50d6467514f42638f1e3dfbed15c9cc49d7656d5a9255a29f536a28c6e90c988c362b24a4e425db7a0cdec22af3216ff0f79d09918a03766d06"

    }

    "Should be able call crypto.nacl_keypair" {
        val client = TonClient()

        val response = client.crypto.naclBoxKeypair()
        response.public shouldNotBe null

    }

    "Should be able call crypto.nacl_box_keypair_from_secret_key" {
        val client = TonClient()

        val keypair = client.crypto.naclBoxKeypair()

        val response = client.crypto.naclBoxKeypairFromSecretKey(keypair.secret)
        response.public shouldBe keypair.public

    }

    "Should be able call crypto.nacl_box and nacl_box_open" {
        val client = TonClient()

        val decrypted = Base64.getEncoder().encodeToString("Test".encodeToByteArray())

        val params = ParamsOfNaclBox(
            decrypted, "cd7f99924bf422544046e83595dd5803f17536f5c9a11746",
            "c4e2d9fe6a6baf8d1812b799856ef2a306291be7a7024837ad33a8530db79c6b",
            "d9b9dc5033fb416134e5d2107fdbacab5aadb297cb82dbdcd137d663bac59f7f"
        )

        val box = client.crypto.naclBox(params)

        box shouldBe "K8GNPPXzoz/R0/TiewGuft/K30s="

        val open = client.crypto.naclBoxOpen(ParamsOfNaclBoxOpen(box, params.nonce, params.theirPublic, params.secret))

        open shouldBe decrypted

    }

    "Should be able call crypto.nacl_secret_box and nacl_secret_box_open" {
        val client = TonClient()

        val decrypted = Base64.getEncoder().encodeToString("Test".encodeToByteArray())

        val params = ParamsOfNaclSecretBox(
            decrypted, "cd7f99924bf422544046e83595dd5803f17536f5c9a11746",
            "c4e2d9fe6a6baf8d1812b799856ef2a306291be7a7024837ad33a8530db79c6b"
        )

        val box = client.crypto.naclSecretBox(params)

        box shouldBe "sUkHiBr1KvemKr4WfwqkhS+JUnA="

        val open = client.crypto.naclSecretBoxOpen(ParamsOfNaclSecretBoxOpen(box, params.nonce, params.key))

        open shouldBe decrypted

    }


    "Should be able call crypto.chacha20" {
        val client = TonClient()

        val text = Base64.getEncoder().encodeToString("teststring".toByteArray())
        val response = client.crypto.chacha20(ParamsOfChaCha20(text, "0x7ef364d02bdf489a56714553dd66260666d52d4b03c5abd6ce62ec7ffbc0a2ca", "0x7ef364d02bdf489a56714555"))

        response shouldBe "iT48EUCm7zmgIw=="

    }
})