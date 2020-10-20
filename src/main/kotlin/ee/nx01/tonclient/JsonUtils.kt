package ee.nx01.tonclient

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue


object JsonUtils {
    val mapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    fun readAbi(abiName: String): Map<Any, Any> {
        val abiStream = JsonUtils::class.java.getResourceAsStream(("/contracts/$abiName"))
        return mapper.readValue(abiStream)
    }


}