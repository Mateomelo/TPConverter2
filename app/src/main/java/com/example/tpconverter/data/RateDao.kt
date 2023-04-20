package com.example.tpconverter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tpconverter.model.Rate

@Dao
interface RateDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRate(rate: Rate)

    @Query("SELECT * FROM rates")
    suspend fun getRates(): List<Rate>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates: List<Rate>)
}
