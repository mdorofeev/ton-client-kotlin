package ee.nx01.tonclient

import com.fasterxml.jackson.annotation.JsonCreator

class TonClientException(val tonClientError: TonClientError) :
        RuntimeException("${tonClientError.message}, Details: $tonClientError") {
}

data class TonClientError(
    val message: String,
    val code: Int,
    val data: Map<String, Any>
)


enum class TonClientErrorCode(val code: Int) {

    UNKNOWN(0);

    companion object {
        @JsonCreator @JvmStatic fun fromIntRepresentation(intValue: Int): TonClientErrorCode {
            return values().firstOrNull { it.code == intValue } ?: UNKNOWN
        }
    }


}