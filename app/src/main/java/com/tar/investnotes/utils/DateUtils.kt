package com.example.q_pulse.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmField
    val PATTERN_TOKEN = "yyyyMMdd"
    val PATTERN_FULL = "yyyy-MM-dd HH:mm"
    val PATTERN_FULL_SMS = "HH:mm dd.MM.yyyy"

    @JvmStatic
    val currentTimeMillis: Long
        get() = System.currentTimeMillis() / 1000

    val date: Date
        get() = Date(currentTimeMillis)

    fun getFormattedDate(pPattern: String, pTimeInMillis: Long): String {
        val dateFormat = SimpleDateFormat(pPattern, Locale.UK)
        return dateFormat.format(pTimeInMillis)
    }

    @JvmStatic
    fun getCurrentFormattedDate(pPattern: String): String {
        return getFormattedDate(pPattern, currentTimeMillis * 1000)
    }
}