package com.example.composetest

import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_FULL = "d MMM YYYY"
const val DATE_FORMAT_YEAR = "d MMM"
const val DATE_FORMAT_WEEK = "EEEE"
const val DATE_FORMAT_TODAY = "hh:mm"
const val SECOND = 1000L
const val MINUTE = 60L * SECOND
const val HOUR = 60L * MINUTE
const val DAY = 24L * HOUR
const val WEEK = 7L * DAY
const val YEAR = 365L * DAY

fun formatDate(timeStamp: Long): String {
    val today = Calendar.getInstance()
    val chatDate = Date(timeStamp)
    val diff = today.timeInMillis - timeStamp
    return when {
        diff >= YEAR -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_FULL, Locale.getDefault())
            dateFormat.format(chatDate)
        }
        diff >= WEEK -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_YEAR, Locale.getDefault())
            dateFormat.format(chatDate)
        }
        diff >= DAY -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_WEEK, Locale.getDefault())
            dateFormat.format(chatDate)
        }
        else -> {
            val dateFormat = SimpleDateFormat(DATE_FORMAT_TODAY, Locale.getDefault())
            dateFormat.format(chatDate)
        }
    }
}