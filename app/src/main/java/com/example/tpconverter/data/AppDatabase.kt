package com.example.tpconverter.data

import Rate
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Rate::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao
}