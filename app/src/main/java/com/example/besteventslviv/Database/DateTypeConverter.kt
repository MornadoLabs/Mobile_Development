package com.example.besteventslviv.Database

import android.arch.persistence.room.TypeConverter
import java.util.*

class DateTypeConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}