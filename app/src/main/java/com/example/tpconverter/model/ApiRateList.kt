package com.example.tpconverter.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ApiRateList(
    var rates: Map<String, Double>,
    var base: String
) {
}