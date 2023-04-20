package com.example.tpconverter.model

import android.util.Log
import com.example.tpconverter.configuration.AppConfig
import java.util.*

object MergeApi {

    fun merge(rates: ApiRateList, currencies: Map<String, String>): MutableList<Rate> {
        var listRates= mutableListOf<Rate>()
        var index = 0
        for(rate in rates.rates){
            listRates.add(Rate(index, rate.key, currencies[rate.key]!!, rate.value, AppConfig.API_FLAG+ rate.key.lowercase(
                Locale.getDefault()
            ) +".png"))
        }
        Log.d("erreur : ", listRates[1].flag)

        return listRates
    }
}