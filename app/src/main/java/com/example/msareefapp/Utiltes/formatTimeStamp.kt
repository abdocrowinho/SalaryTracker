package com.example.msareefapp.Utiltes

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getDate():String {
    val sdf =SimpleDateFormat("dd / MM / yy", Locale("en","Us"))
    val currentDate = Date()
    return sdf.format(currentDate)
}

fun getDayOfWeek():String{
    val sdf = SimpleDateFormat("EEEE",Locale("en","Us"))
    val currentDay=Date()
    return sdf.format(currentDay)
}