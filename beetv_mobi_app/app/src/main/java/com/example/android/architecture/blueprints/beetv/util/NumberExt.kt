package com.example.android.architecture.blueprints.beetv.util

fun Int.formatOffset() : String{
    val sb = StringBuilder("UTC").append(if (toInt() < 0) "-" else "+")
    var secs = Math.abs(toInt()) / 1000
    val hours = secs / 3600
    secs -= hours * 3600
    val mins = secs / 60
    sb.append(if (hours < 10) "0" else "").append(hours).append(":")
    sb.append(if (mins < 10) "0" else "").append(mins)
    return sb.toString()
}