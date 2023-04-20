package com.example.tpconverter.json

import android.util.Log
import com.example.tpconverter.model.ApiRateList
import com.fasterxml.jackson.module.kotlin.jsonMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule

object RateConverter {
    fun convertRates(data: String): ApiRateList {


        val objectMapper = jsonMapper {
            addModule(kotlinModule())
        }
        val result: ApiRateList = objectMapper.readValue(
            data,
            ApiRateList::class.java
        )
        return result

    }
}