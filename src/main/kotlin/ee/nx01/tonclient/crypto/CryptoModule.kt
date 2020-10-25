package ee.nx01.tonclient.crypto

import com.fasterxml.jackson.annotation.JsonValue
import ee.nx01.tonclient.JsonUtils
import ee.nx01.tonclient.TonClient
import ee.nx01.tonclient.abi.KeyPair


class CryptoModule(private val tonClient: TonClient) {

    suspend fun ed25519Keypair(): KeyPair {
        return JsonUtils.read(this.tonClient.request("crypto.generate_random_sign_keys", ""))
    }

    suspend fun mnemonicWords(params: TONMnemonicWordsParams): String {
        return this.tonClient.request("crypto.mnemonic.words", "")
    }

    suspend fun mnemonicFromRandom(params: TONMnemonicFromRandomParams): String {
        return this.tonClient.request("crypto.mnemonic.from.random", "")
    }

    suspend fun mnemonicDeriveSignKeys(params: TONMnemonicDeriveSignKeysParams): KeyPair {
        return JsonUtils.read(this.tonClient.request("crypto.mnemonic_derive_sign_keys", params))
    }

    suspend fun sign(params: ParamsOfSign): ResultOfSign {
        return JsonUtils.read(this.tonClient.request("crypto.sign", params))
    }

    suspend fun verifySignature(params: ParamsOfVerifySignature): ResultOfVerifySignature {
        return JsonUtils.read(this.tonClient.request("crypto.verify_signature", params))
    }


}

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

enum class TONMnemonicDictionaryType {
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
enum class TONMnemonicWordCountType(val count: Int) {
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

data class TONMnemonicWordsParams(
    val dictionary: TONMnemonicDictionaryType,
    val wordCount: TONMnemonicWordCountType
)

data class TONMnemonicFromRandomParams(
    val dictionary: TONMnemonicDictionaryType,
    val wordCount: TONMnemonicWordCountType,
)

data class TONMnemonicDeriveSignKeysParams(
    val dictionary: TONMnemonicDictionaryType,
    val wordCount: TONMnemonicWordCountType,
    val phrase: String,
    val path: String,
    val compliant: Boolean = false,
)
