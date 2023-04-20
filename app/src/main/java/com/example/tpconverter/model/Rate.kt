package com.example.tpconverter.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates")
class Rate(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "currency_code")
    var code: String = "",

    @ColumnInfo(name = "currency_label")
    var label: String = "",

    @ColumnInfo(name = "currency_value")
    var value: Double = 0.0,

    @ColumnInfo(name = "currency_flag")
    var flag: String = ""
)
