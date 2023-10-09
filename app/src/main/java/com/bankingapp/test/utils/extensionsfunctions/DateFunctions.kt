package com.bankingapp.test.utils.extensionsfunctions

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// get the hours date
internal fun Date.convertFormatHour():String {
    val dateFormatter = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
    return dateFormatter.format(this)
}

//get the format date
internal fun Date.convertFormatDate():String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormatter.format(this)
}

// get the format full of date
internal fun Date.convertFormatFull():String {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault())
    return dateFormatter.format(this)
}