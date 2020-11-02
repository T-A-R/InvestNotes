package com.tar.investnotes.api.responses.index

import com.google.gson.annotations.SerializedName

class Rows {
    @SerializedName("row")
    var row: List<Row>? = null
}