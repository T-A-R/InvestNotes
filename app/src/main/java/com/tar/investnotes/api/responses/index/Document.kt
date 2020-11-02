package com.tar.investnotes.api.responses.index

import com.google.gson.annotations.SerializedName

class Document {
    @SerializedName("data")
    var data: Data? = null
}