package com.bankingapp.test.utils.calendar

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import com.bankingapp.test.utils.extensionsfunctions.convertFormatFull
import java.util.Calendar

object CalendarUtils {

    //Open calendar
    fun pickDateTime(listener: CalendarListener, context: Context) {
        val currentDateTime = Calendar.getInstance()

        currentDateTime.add(Calendar.YEAR, -10)
        val startYear = currentDateTime.get(Calendar.YEAR)
        val startMonth = currentDateTime.get(Calendar.MONTH)
        val startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        val startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        val startMinute = currentDateTime.get(Calendar.MINUTE)

        val datePickerDialog = DatePickerDialog(context, { _, year, month, day ->
            TimePickerDialog(
                context,
                { _, hour, minute ->
                    val pickedDateTime = Calendar.getInstance()
                    pickedDateTime.set(year, month, day, hour, minute)
                    listener.setDate(pickedDateTime.time.convertFormatFull(), pickedDateTime.time)
                },
                startHour,
                startMinute,
                false
            ).show()
        }, startYear, startMonth, startDay)
        datePickerDialog.datePicker.maxDate = currentDateTime.timeInMillis
        datePickerDialog.show()
    }
}
