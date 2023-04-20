package com.example.tpconverter.json
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object CurrencyConverter {
    fun convertCurrencies(jsonData: String): Map<String, String> {

        // ObjectMapper
        val objectMapper = jsonMapper {
            addModule(kotlinModule())
        }
        // Deserialize JSON Data
        val result: Map<String, String> = objectMapper.readValue(
            jsonData,
            object : TypeReference<Map<String, String>>() {})

        return result
    }
}