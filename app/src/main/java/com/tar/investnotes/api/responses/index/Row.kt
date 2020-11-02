package com.tar.investnotes.api.responses.index

import com.google.gson.annotations.SerializedName

class Row {
    @SerializedName("-secid")
    var secid: String? = null

    @SerializedName("-shortname")
    var shortname: String? = null

    @SerializedName("-name")
    var name: String? = null

    @SerializedName("-primary_boardid")
    var primary_boardid: String? = null

    @SerializedName("-emitent_title")
    var emitent_title: String? = null

}