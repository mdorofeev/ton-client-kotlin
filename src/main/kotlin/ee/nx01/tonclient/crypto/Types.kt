package ee.nx01.tonclient.crypto

import com.fasterxml.jackson.annotation.JsonValue
import ee.nx01.tonclient.abi.KeyPair


data class ParamsOfScrypt(
    val password: String,
    val salt: String,
    val logN: Int = 15,
    val r: Int = 8,
    val p: Int = 1,
    val dkLen: Int = 32
)

data class ResultOfHDKeyPublicFromXPrv(
    val public: String
)

data class ParamsOfHDKeyPublicFromXPrv(
    val xprv: String
)

data class ResultOfHDKeySecretFromXPrv(
    val secret: String
)

data class ParamsOfHDKeySecretFromXPrv(
    val xprv: String
)

data class ResultOfHDKeyDeriveFromXPrvPath(
    val xprv: String
)

data class ParamsOfHDKeyDeriveFromXPrvPath(
    val xprv: String,
    val path: String
)


data class ResultOfHDKeyDeriveFromXPrv(
    val xprv: String
)


data class ParamsOfHDKeyDeriveFromXPrv(
    val xprv: String,
    val childIndex: Int,
    val hardened: Boolean
)


data class ParamsOfHDKeyXPrvFromMnemonic(
    val phrase: String,
    val dictionary: Int? = null,
    val wordCount: Int? = null
)

data class ResultOfHDKeyXPrvFromMnemonic(
    val xprv: String
)

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

data class ParamsOfNaclSecretBoxOpen(
    val encrypted: String,
    val nonce: String,
    val key: String
)

data class ParamsOfNaclSecretBox(
    val decrypted: String,
    val nonce: String,
    val key: String
)

data class ParamsOfNaclBox(
    val decrypted: String,
    val nonce: String,
    val theirPublic: String,
    val secret: String
)

data class ParamsOfNaclBoxOpen(
    val encrypted: String,
    val nonce: String,
    val theirPublic: String,
    val secret: String
)

