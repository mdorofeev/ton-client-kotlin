package ee.nx01.tonclient.boc

data class BocCacheType(
    val type: BocCacheTypeEnum = BocCacheTypeEnum.Unpinned,
    val pin: String? = null
)

enum class BocCacheTypeEnum {
    Pinned, Unpinned
}

data class ParamsOfEncodeBoc(
    val builder: List<BuilderOp>,
    val boc_cache: BocCacheType?
)

/**
 *  Depends on value of the type field.

    When type is 'Integer'

    Append integer to cell data.

    size: number – Bit size of the value.
    value: any – Value: - Number containing integer number.
    e.g. 123, -123. - Decimal string. e.g. "123", "-123".
    - 0x prefixed hexadecimal string.
    e.g 0x123, 0X123, -0x123.
    When type is 'BitString'

    Append bit string to cell data.

    value: string – Bit string content using bitstring notation. See TON VM specification 1.0.
    Contains hexadecimal string representation:
    - Can end with _ tag.
    - Can be prefixed with x or X.
    - Can be prefixed with x{ or X{ and ended with }.

    Contains binary string represented as a sequence
    of 0 and 1 prefixed with n or N.

    Examples:
    1AB, x1ab, X1AB, x{1abc}, X{1ABC}
    2D9_, x2D9_, X2D9_, x{2D9_}, X{2D9_}
    n00101101100, N00101101100
    When type is 'Cell'

    Append ref to nested cells

    builder: BuilderOp[] – Nested cell builder
    When type is 'CellBoc'

    Append ref to nested cell

    boc: string – Nested cell BOC encoded with base64 or BOC cache key.
    Variant constructors:

    function builderOpInteger(size: number, value: any): BuilderOp;
    function builderOpBitString(value: string): BuilderOp;
    function builderOpCell(builder: BuilderOp[]): BuilderOp;
    function builderOpCellBoc(boc: string): BuilderOp;
 */
data class BuilderOp(
    val type: BuilderOpType,
    val size: Int? = null,
    val value: Any? = null,
    val builder: List<BuilderOp>? = null,
    val boc: String? = null,
    val address: String? = null
)

enum class BuilderOpType {
    Integer, BitString, Cell, CellBoc, Address
}

data class ResultOfEncodeBoc(
    val boc: String
)

data class ResultOfGetBocHash(
    val hash: String
)

data class ParamsOfGetBocHash(
    val boc: String
)

data class ParamsOfGetCodeFromTvc(
    val tvc: String
)

data class ResultOfGetCodeFromTvc(
    val code: String
)

data class ParamsOfParseShardstate(
    val boc: String,
    val id: String,
    val workchainId: Int
)

data class ResultOfGetBlockchainConfig(
    val configBoc: String
)

data class ParamsOfGetBlockchainConfig(
    val blockBoc: String
)

data class ParamsOfParse(
    val boc: String
)

data class ResultOfParse(
    val parsed: Map<String, Any>
)


data class ResultOfGetCompilerVersion(
    val version: String?
)

data class ParamsOfGetCompilerVersion(
    val code: String
)

data class ResultOfDecodeTvc(
    val code: String? = null,
    val code_hash: String? = null,
    val code_depth: Int? = null,
    val data: String? = null,
    val dataHash: String? = null,
    val data_depth: Int? = null,
    val library: String? = null,
    val tick: Boolean? = null,
    val tock: Boolean? = null,
    val split_depth: Int? = null,
    val compilerVersion: String? = null
)

data class ParamsOfDecodeTvc(
    val tvc: String,
    val bocCache: BocCacheType? = null
)

data class ParamsOfEncodeTvc(
    val code: String? = null,
    val data: String? = null,
    val library: String? = null,
    val tick: Boolean? = null,
    val tock: Boolean? = null,
    val splitDepth: Int? = null,
    val bocCache: BocCacheType? = null
)

data class ResultOfEncodeTvc(
    val tvc: String
)

data class ResultOfSetCodeSalt(
    val code: String
)

data class ParamsOfSetCodeSalt(
    val code: String,
    val salt: String,
    val bocCache: BocCacheType? = null
)

data class ParamsOfGetCodeSalt(
    val code: String,
    val bocCache: BocCacheType? = null
)

data class ResultOfGetCodeSalt(
    val salt: String?
)

data class ParamsOfGetBocDepth(
    val boc: String
)

data class ResultOfGetBocDepth(
    val depth: Int
)

data class ParamsOfEncodeExternalInMessage(
    val src: String? = null,
    val dst: String,
    val init: String? = null,
    val body: String? = null,
    val boc_cache: BocCacheType? = null
)

data class ResultOfEncodeExternalInMessage(
    val message: String,
    val message_id: String
)
