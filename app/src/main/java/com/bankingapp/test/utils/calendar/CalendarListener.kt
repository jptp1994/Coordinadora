package com.bankingapp.test.utils.calendar

import java.util.Date

interface CalendarListener {
    fun setDate(convertFormatFull: String, time: Date)
}