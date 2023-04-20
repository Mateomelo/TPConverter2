package com.example.tpconverter.data

import androidx.room.Dao
import androidx.room.Query
import com.example.tpconverter.model.Rate

@Dao
interface RateDao : BaseDao<Rate> {
    @Query("SELECT * FROM rates")
    suspend fun getRates(): List<Rate>
}

