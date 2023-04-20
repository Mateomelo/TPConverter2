package com.example.tpconverter.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.*
import androidx.room.Query

@Dao
interface BaseDao<T> {
    @Insert(onConflict = REPLACE)
    suspend fun insert(entity: T)

    @Insert(onConflict = REPLACE)
    suspend fun insertAll(entities: List<T>)
}
