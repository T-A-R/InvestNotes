package com.tar.investnotes.database.models

class StockSymbolR {
    var symbol: String? = null
    var name: String? = null
    var exch: String? = null
    var type: String? = null
    var exchDisp: String? = null
    var typeDisp: String? = null

    constructor() {}
    constructor(symbol: String?, name: String?, exch: String?, type: String?, exchDisp: String?, typeDisp: String?) {
        this.symbol = symbol
        this.name = name
        this.exch = exch
        this.type = type
        this.exchDisp = exchDisp
        this.typeDisp = typeDisp
    }

    override fun toString(): String {
        return String.format("Symbol: %s, name: %s", symbol, name)
    }
}