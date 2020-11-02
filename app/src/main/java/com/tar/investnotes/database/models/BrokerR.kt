package com.tar.investnotes.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tar.investnotes.Constants

@Entity
data class BrokerR(

    @PrimaryKey
    @ColumnInfo(name = "name")
    var name: String = "",
)