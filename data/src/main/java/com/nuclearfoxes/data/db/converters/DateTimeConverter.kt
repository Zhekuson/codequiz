package com.nuclearfoxes.data.db.converters

import androidx.room.TypeConverter
import java.time.OffsetDateTime
import java.util.*

object DateTimeConverter {
    @TypeConverter
    fun fromTimestamp(value:Long): Date {
      return Date(value)
    }
    @TypeConverter
    fun dateToTimestamp(date:Date):Long {
        return date.getTime()
    }
}