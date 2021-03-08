package com.example.composetest

import com.example.composetest.ui.chatlist.*
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_FULL = "d MMM YYYY"
const val DATE_FORMAT_YEAR = "d MMM"
const val DATE_FORMAT_WEEK = "EEEE"
const val DATE_FORMAT_TODAY = "hh:mm"
const val DAY = 1000 * 60 * 60 * 24L
const val WEEK = DAY * 7L
const val YEAR = DAY * 365L

fun formatDate(timeStamp: Long): String {
    val today = Calendar.getInstance()
    val chatDate = Date(timeStamp * 1000)
    val diff = today.timeInMillis - timeStamp * 1000
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