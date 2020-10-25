package ee.nx01.tonclient

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue


object JsonUtils {
    val mapper: ObjectMapper = ObjectMapper()
        .registerModule(KotlinModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS)

    inline fun <reified T> read(json: String): T = mapper.readValue(json)

    fun write(obj: Any): String = mapper.writeValueAsString(obj)

}