package com.example.besteventslviv.Helpers

import java.text.SimpleDateFormat
import java.util.*

class DateHelper {
    companion object {
        fun DateToString(date: Date): String {
            var formatter = SimpleDateFormat("d MMM yyyy")
            return formatter.format(date)
        }

        fun DateTimeToString(date: Date): String {
            var formatter = SimpleDateFormat("d MMM yyyy HH:mm a")
            return formatter.format(date)
        }
    }
}