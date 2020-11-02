package com.tar.investnotes.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("type"), Index("active")])
data class InvestmentR(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "code")
    var code: String = "",

    @ColumnInfo(name = "quantity")
    var quantity: Int = 0,

    @ColumnInfo(name = "currency")
    var currency: String = "$",

    @ColumnInfo(name = "price_buy")
    var priceBuy: Float = 0F,

    @ColumnInfo(name = "price_sell")
    var priceSell: Float = 0F,

    @ColumnInfo(name = "price_last")
    var priceLast: Float = 0F,

    @ColumnInfo(name = "owner")
    var owner: String = "",

    @ColumnInfo(name = "broker")
    var broker: String = "",

    @ColumnInfo(name = "type")
    var type: String = "",

    @ColumnInfo(name = "commission")
    var commission: Float = 0F,

    @ColumnInfo(name = "date")
    var date: Long = 0L,

    @ColumnInfo(name = "active")
    var active: Boolean = true,

    )