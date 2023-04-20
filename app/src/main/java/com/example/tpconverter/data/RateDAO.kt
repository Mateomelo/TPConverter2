package com.example.tpconverter.data

import Rate
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface RateDao {
    @Insert
    suspend fun insert(rate: Rate)

    @Update
    suspend fun update(rate: Rate)

    @Delete
    suspend fun delete(rate: Rate)

    @Query("SELECT * FROM rates")
    suspend fun getAllRates(): List<Rate>
}
