package com.example.msareefapp.Utiltes

import java.sql.Timestamp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun getDate(locale: Locale):String {
    val sdf =SimpleDateFormat("dd / MM / yy",locale)
    val currentDate = Date()
    return sdf.format(currentDate)
}
fun getDayOfWeek(date: String,locale: Locale):String{
    try {
        val sdf = SimpleDateFormat("dd / MM / yy", locale)
        val parsedDate = sdf.parse(date)
        val dayOfWeekFormat = SimpleDateFormat("EEEE", locale)
        return parsedDate?.let { dayOfWeekFormat.format(it) }.toString()
    } catch (e: ParseException) {
        e.printStackTrace()
        return "Unknown"
    }
}