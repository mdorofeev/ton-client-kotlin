package ee.nx01.tonclient.crypto

import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.KeyPair


class CryptoModule(private val tonClient: TonClient) {

    /**
    ## scrypt

    Derives key from `password` and `key` using `scrypt` algorithm. See [https://en.wikipedia.org/wiki/Scrypt].

    # Arguments
    - `log_n` - The log2 of the Scrypt parameter `N`
    - `r` - The Scrypt parameter `r`
    - `p` - The Scrypt parameter `p`
    # Conditions
    - `log_n` must be less than `64`
    - `r` must be greater than `0` and less than or equal to `4294967295`
    - `p` must be greater than `0` and less than `4294967295`
    # Recommended values sufficient for most use-cases
    - `log_n = 15` (`n = 32768`)
    - `r = 8`
    - `p = 1`
     */
    suspend fun scrypt(params: ParamsOfScrypt): String {
        val response = this.tonClient.requestString("crypto.scrypt", params)
        return JsonUtils.read<Map<String,String>>(response)["key"] ?: throw RuntimeException()
    }

    suspend fun ed25519Keypair(): KeyPair {
        return this.tonClient.request("crypto.generate_random_sign_keys", "")
    }

    suspend fun mnemonicFromRandom(params: MnemonicFromRandomParams): String {
        val response = this.tonClient.requestString("crypto.mnemonic_from_random", params)
        return JsonUtils.read<Map<String,String>>(response)["phrase"] ?: ""
    }

    /**
    ## mnemonic_verify

    The phrase supplied will be checked for word length and validated according to the checksum specified in BIP0039.
     */
    suspend fun mnemonicVerify(params: ParamsOfMnemonicVerify): Boolean {
        val response = this.tonClient.requestString("crypto.mnemonic_verify", params)
        return JsonUtils.read<Map<String,Boolean>>(response)["valid"] ?: false
    }

    suspend fun mnemonicDeriveSignKeys(params: MnemonicDeriveSignKeysParams): KeyPair {
        return this.tonClient.request("crypto.mnemonic_derive_sign_keys", params)
    }

    suspend fun hdkeyXprvFromMnemonic(params: ParamsOfHDKeyXPrvFromMnemonic): ResultOfHDKeyXPrvFromMnemonic {
        return this.tonClient.request("crypto.hdkey_xprv_from_mnemonic", params)
    }

    suspend fun hdkeyDeriveFromXprv(params: ParamsOfHDKeyDeriveFromXPrv): ResultOfHDKeyDeriveFromXPrv {
        return this.tonClient.request("crypto.hdkey_derive_from_xprv", params)
    }

    suspend fun hdkeyDeriveFromXprvPath(params: ParamsOfHDKeyDeriveFromXPrvPath): ResultOfHDKeyDeriveFromXPrvPath {
        return this.tonClient.request("crypto.hdkey_derive_from_xprv_path", params)
    }

    suspend fun hdkeySecretFromXprv(params: ParamsOfHDKeySecretFromXPrv): ResultOfHDKeySecretFromXPrv {
        return this.tonClient.request("crypto.hdkey_secret_from_xprv", params)
    }

    suspend fun hdkeyPublicFromXprv(params: ParamsOfHDKeyPublicFromXPrv): ResultOfHDKeyPublicFromXPrv {
        return this.tonClient.request("crypto.hdkey_public_from_xprv", params)
    }

    suspend fun sign(params: ParamsOfSign): ResultOfSign {
        return this.tonClient.request("crypto.sign", params)
    }

    suspend fun verifySignature(params: ParamsOfVerifySignature): ResultOfVerifySignature {
        return this.tonClient.request("crypto.verify_signature", params)
    }

    suspend fun keyPairFromSurfMnemonicPhrase(phrase: String): KeyPair {
        return tonClient.crypto.mnemonicDeriveSignKeys(
            MnemonicDeriveSignKeysParams(
                MnemonicDictionaryType.ENGLISH, MnemonicWordCountType.WORDS12,
                phrase, HD_PATH
            )
        )
    }

    companion object {
        val HD_PATH = "m/44'/396'/0'/0/0"
    }

}
