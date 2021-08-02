package com.tar.investnotes.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SettingsR(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "currency")
    var code: Int = 1,

    @ColumnInfo(name = "lang")
    var lang: String = "en",

    @ColumnInfo(name = "user")
    var user: Long = 0L,

    )