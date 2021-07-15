package com.tar.investnotes.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index("id"), Index("symbol")])
class StockSymbolR(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,
    @ColumnInfo(name = "symbol")
    var symbol: String? = null,
    @ColumnInfo(name = "name")
    var name: String? = null,
    @ColumnInfo(name = "exch")
    var exch: String? = null,
    @ColumnInfo(name = "type")
    var type: String? = null
) {
    override fun toString(): String {
        return String.format("Symbol: $symbol, name: $name")
    }
}