package ee.nx01.tonclient.crypto

import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.KeyPair
import java.util.*


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
        val response = this.tonClient.request<Map<String, String>>("crypto.scrypt", params)
        return response["key"] ?: throw RuntimeException()
    }

    suspend fun sha256(data: String): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.sha256", mapOf("data" to data))
        return response["hash"] ?: throw RuntimeException()
    }

    /**
    ## factorize

    Performs prime factorization – decomposition of a composite number into a product of smaller prime integers (factors). See [https://en.wikipedia.org/wiki/Integer_factorization]
     */
    suspend fun factorize(composite: String): List<String> {
        val response = this.tonClient.request<Map<String, List<String>>>("crypto.factorize", mapOf("composite" to composite))
        return response["factors"] ?: throw RuntimeException()
    }


    /**
    ## modular_power

    Performs modular exponentiation for big integers (`base`^`exponent` mod `modulus`). See [https://en.wikipedia.org/wiki/Modular_exponentiation]
     */
    suspend fun modularPower(base: String, exponent: String, modulus: String): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.modular_power",
            mapOf("base" to base, "exponent" to exponent, "modulus" to modulus))
        return response["modular_power"] ?: throw RuntimeException()
    }

    suspend fun tonCrc16(data: String): Int {
        val response = this.tonClient.request<Map<String, Int>>("crypto.ton_crc16", mapOf("data" to data))
        return response["crc"] ?: throw RuntimeException()
    }

    suspend fun generateRandomBytesBase64(length: Int): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.generate_random_bytes", mapOf("length" to length))
        return response["bytes"] ?: throw RuntimeException()
    }

    suspend fun generateRandomBytes(length: Int): ByteArray {
        return Base64.getDecoder().decode(generateRandomBytesBase64(length))
    }

    suspend fun sha512(data: String): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.sha512", mapOf("data" to data))
        return response["hash"] ?: throw RuntimeException()
    }

    suspend fun convertPublicKeyToTonSafeFormat(publicKey: String): String {
        val response = this.tonClient.request<Map<String, String>>(
            "crypto.convert_public_key_to_ton_safe_format",
            mapOf("public_key" to publicKey)
        )
        return response["ton_public_key"] ?: throw RuntimeException()
    }

    suspend fun ed25519Keypair(): KeyPair {
        return this.tonClient.request("crypto.generate_random_sign_keys", "")
    }

    suspend fun mnemonicFromRandom(params: MnemonicFromRandomParams): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.mnemonic_from_random", params)
        return response["phrase"] ?: throw RuntimeException()
    }

    /**
    ## mnemonic_verify

    The phrase supplied will be checked for word length and validated according to the checksum specified in BIP0039.
     */
    suspend fun mnemonicVerify(params: ParamsOfMnemonicVerify): Boolean {
        val response = this.tonClient.request<Map<String, Boolean>>("crypto.mnemonic_verify", params)
        return response["valid"] ?: false
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
