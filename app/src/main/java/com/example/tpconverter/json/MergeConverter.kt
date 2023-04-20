package com.example.tpconverter.json

import android.util.Log
import com.example.tpconverter.configuration.AppConfig
import com.example.tpconverter.model.ApiRateList
import com.example.tpconverter.model.Rate
import java.util.*

object MergeConverter {

    fun merge(rates: ApiRateList, currencies: Map<String, String>): MutableList<Rate> {
        var listRates= mutableListOf<Rate>()
        var index = 0
        for(rate in rates.rates){
            listRates.add(
                Rate(index, rate.key, currencies[rate.key]!!, rate.value, AppConfig.API_FLAG+ rate.key.lowercase(
                Locale.getDefault()
            ) +".png")
            )
            index++
            Log.d("rate", rate.toString())
        }
        Log.d("rates", listRates.toString())
        return listRates
    }
}