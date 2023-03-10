package jp.matsuura.householdaccountapp.data.database

import androidx.room.TypeConverter
import java.util.*

class Converter {
    @TypeConverter
    fun fromTimestamp(value: Long): Date {
        return Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }
}