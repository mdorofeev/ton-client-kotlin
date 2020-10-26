package ee.nx01.tonclient.crypto

import com.fasterxml.jackson.annotation.JsonValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.KeyPair


class CryptoModule(private val tonClient: TonClient) {
    val HD_PATH = "m/44'/396'/0'/0/0"

    suspend fun ed25519Keypair(): KeyPair {
        return JsonUtils.read(this.tonClient.request("crypto.generate_random_sign_keys", ""))
    }

    suspend fun mnemonicFromRandom(params: MnemonicFromRandomParams): String {
        val response = this.tonClient.request("crypto.mnemonic_from_random", params)
        return JsonUtils.read<Map<String,String>>(response)["phrase"] ?: ""
    }

    suspend fun mnemonicVerify(params: ParamsOfMnemonicVerify): Boolean {
        val response = this.tonClient.request("crypto.mnemonic_verify", params)
        return JsonUtils.read<Map<String,Boolean>>(response)["valid"] ?: false
    }

    suspend fun mnemonicDeriveSignKeys(params: MnemonicDeriveSignKeysParams): KeyPair {
        return JsonUtils.read(this.tonClient.request("crypto.mnemonic_derive_sign_keys", params))
    }

    suspend fun sign(params: ParamsOfSign): ResultOfSign {
        return JsonUtils.read(this.tonClient.request("crypto.sign", params))
    }

    suspend fun verifySignature(params: ParamsOfVerifySignature): ResultOfVerifySignature {
        return JsonUtils.read(this.tonClient.request("crypto.verify_signature", params))
    }

    suspend fun keyPairFromSurfMnemonicPhrase(phrase: String): KeyPair {
        return tonClient.crypto.mnemonicDeriveSignKeys(
            MnemonicDeriveSignKeysParams(
                MnemonicDictionaryType.ENGLISH, MnemonicWordCountType.WORDS12,
                phrase, HD_PATH
            )
        )
    }

}

data class ParamsOfMnemonicVerify(
    val phrase: String,
    val dictionary: Int? = null,
    val wordCount: Int? = null
)

data class ParamsOfVerifySignature(
    val signed: String,
    val public: String
)

data class ResultOfVerifySignature(
    val unsigned: String
)


data class ParamsOfSign(
    val unsigned: String,
    val keys: KeyPair
)

data class ResultOfSign(
    val signed: String,
    val signature: String
)

enum class MnemonicDictionaryType {
    TON,
    ENGLISH,
    CHINESE_SIMPLIFIED,
    CHINESE_TRADITIONAL,
    FRENCH,
    ITALIAN,
    JAPANESE,
    KOREAN,
    SPANISH;

    @JsonValue
    fun toValue(): Int {
        return ordinal
    }
}
enum class MnemonicWordCountType(val count: Int) {
    WORDS12(12),
    WORDS15(15),
    WORDS18(18),
    WORDS21(21),
    WORDS24(24);

    @JsonValue
    open fun toValue(): Int {
        return count
    }
}

data class MnemonicWordsParams(
    val dictionary: MnemonicDictionaryType,
    val wordCount: MnemonicWordCountType
)

data class MnemonicFromRandomParams(
    val dictionary: MnemonicDictionaryType = MnemonicDictionaryType.ENGLISH,
    val wordCount: MnemonicWordCountType = MnemonicWordCountType.WORDS12,
)

data class MnemonicDeriveSignKeysParams(
    val dictionary: MnemonicDictionaryType,
    val wordCount: MnemonicWordCountType,
    val phrase: String,
    val path: String,
    val compliant: Boolean = false,
)
