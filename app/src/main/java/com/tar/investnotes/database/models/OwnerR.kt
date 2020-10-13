package com.tar.investnotes.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tar.investnotes.Constants

@Entity
data class UserR(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0L,

    @ColumnInfo(name = "login")
    var login: String = "",

    @ColumnInfo(name = "password")
    var password: String= "",

    @ColumnInfo(name = "email")
    var email: String= "",

    @ColumnInfo(name = "user_type")
    var user_type: String = Constants.User.FREE,

    @ColumnInfo(name = "phone")
    var phone: Int = 0,

    @ColumnInfo(name = "reg_time")
    val regTime: Long = System.currentTimeMillis(),
)