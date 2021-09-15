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
        return response["key"] ?: error("Incorrect response")
    }

    suspend fun sha256(data: String): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.sha256", mapOf("data" to data))
        return response["hash"] ?: error("Incorrect response")
    }

    /**
    ## factorize

    Performs prime factorization – decomposition of a composite number into a product of smaller prime integers (factors). See [https://en.wikipedia.org/wiki/Integer_factorization]
     */
    suspend fun factorize(composite: String): List<String> {
        val response =
            this.tonClient.request<Map<String, List<String>>>("crypto.factorize", mapOf("composite" to composite))
        return response["factors"] ?: error("Incorrect response")
    }


    /**
    ## modular_power

    Performs modular exponentiation for big integers (`base`^`exponent` mod `modulus`). See [https://en.wikipedia.org/wiki/Modular_exponentiation]
     */
    suspend fun modularPower(base: String, exponent: String, modulus: String): String {
        val response = this.tonClient.request<Map<String, String>>(
            "crypto.modular_power",
            mapOf("base" to base, "exponent" to exponent, "modulus" to modulus)
        )
        return response["modular_power"] ?: error("Incorrect response")
    }

    suspend fun tonCrc16(data: String): Int {
        val response = this.tonClient.request<Map<String, Int>>("crypto.ton_crc16", mapOf("data" to data))
        return response["crc"] ?: error("Incorrect response")
    }

    suspend fun generateRandomBytesBase64(length: Int): String {
        val response =
            this.tonClient.request<Map<String, String>>("crypto.generate_random_bytes", mapOf("length" to length))
        return response["bytes"] ?: error("Incorrect response")
    }

    suspend fun generateRandomBytes(length: Int): ByteArray {
        return Base64.getDecoder().decode(generateRandomBytesBase64(length))
    }

    suspend fun sha512(data: String): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.sha512", mapOf("data" to data))
        return response["hash"] ?: error("Incorrect response")
    }

    suspend fun convertPublicKeyToTonSafeFormat(publicKey: String): String {
        val response = this.tonClient.request<Map<String, String>>(
            "crypto.convert_public_key_to_ton_safe_format",
            mapOf("public_key" to publicKey)
        )
        return response["ton_public_key"] ?: error("Incorrect response")
    }

    suspend fun ed25519Keypair(): KeyPair {
        return this.tonClient.request("crypto.generate_random_sign_keys", "")
    }

    suspend fun mnemonicFromRandom(params: MnemonicFromRandomParams): String {
        val response = this.tonClient.request<Map<String, String>>("crypto.mnemonic_from_random", params)
        return response["phrase"] ?: error("Incorrect response")
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

    suspend fun naclSignKeypairFromSecretKey(secret: String): KeyPair {
        return this.tonClient.request("crypto.nacl_sign_keypair_from_secret_key", mapOf("secret" to secret))
    }

    suspend fun naclSign(unsigned: String, secret: String): String {
        val paramsOfNaClSign = mapOf("unsigned" to unsigned, "secret" to secret)
        return this.tonClient.request<Map<String, String>>("crypto.nacl_sign", paramsOfNaClSign)["signed"]
            ?: error("Incorrect response")
    }

    suspend fun naclSignOpen(signed: String, public: String): String {
        val paramsOfNaClSignOpen = mapOf("signed" to signed, "public" to public)
        return this.tonClient.request<Map<String, String>>("crypto.nacl_sign_open", paramsOfNaClSignOpen)["unsigned"]
            ?: error("Incorrect response")
    }

    suspend fun naclSignDetached(unsigned: String, secret: String): String {
        val paramsOfNaClSignDetached = mapOf("unsigned" to unsigned, "secret" to secret)
        return this.tonClient.request<Map<String, String>>(
            "crypto.nacl_sign_detached",
            paramsOfNaClSignDetached
        )["signature"]
            ?: error("Incorrect response")
    }

    suspend fun naclBoxKeypair(): KeyPair {
        return this.tonClient.request("crypto.nacl_box_keypair", "")
    }

    suspend fun naclBoxKeypairFromSecretKey(secret: String): KeyPair {
        return this.tonClient.request("crypto.nacl_box_keypair_from_secret_key", mapOf("secret" to secret))
    }

    suspend fun naclBox(params: ParamsOfNaclBox): String {
        return this.tonClient.request<Map<String, String>>("crypto.nacl_box", params)["encrypted"]
            ?: error("Incorrect response")
    }

    suspend fun naclBoxOpen(params: ParamsOfNaclBoxOpen): String {
        return this.tonClient.request<Map<String, String>>("crypto.nacl_box_open", params)["decrypted"]
            ?: error("Incorrect response")
    }

    suspend fun naclSecretBox(params: ParamsOfNaclSecretBox): String {
        return this.tonClient.request<Map<String, String>>("crypto.nacl_secret_box", params)["encrypted"]
            ?: error("Incorrect response")
    }

    suspend fun naclSecretBoxOpen(params: ParamsOfNaclSecretBoxOpen): String {
        return this.tonClient.request<Map<String, String>>("crypto.nacl_secret_box_open", params)["decrypted"]
            ?: error("Incorrect response")
    }

    suspend fun naclSignDetachedVerify(params: ParamsOfNaclSignDetachedVerify): Boolean {
        return this.tonClient.request<Map<String, String>>("crypto.nacl_sign_detached_verify", params)["succeeded"].toBoolean()
    }

    suspend fun chacha20(params: ParamsOfChaCha20): String {
        return this.tonClient.request<Map<String, String>>("crypto.chacha20", params)["data"]
            ?: error("Incorrect response")
    }

    suspend fun createEncryptionBox(params: ParamsOfCreateEncryptionBox): RegisteredEncryptionBox {
        return this.tonClient.request("crypto.create_encryption_box", params)
    }

    companion object {
        val HD_PATH = "m/44'/396'/0'/0/0"
    }

}

data class ParamsOfCreateEncryptionBox(
    val algorithm: EncryptionAlgorithm
)

data class EncryptionAlgorithm(
    val type: String = "AES",
    val value: AesParam
)

data class AesParam(
    val mode: CipherMode,
    val key: String,
    val iv: String? = null,
)


enum class CipherMode {
    CBC,
    CFB,
    CTR,
    ECB,
    OFB
}

data class RegisteredEncryptionBox(
    val handle: Long
)
