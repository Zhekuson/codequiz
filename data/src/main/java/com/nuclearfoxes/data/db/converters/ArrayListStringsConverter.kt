package com.nuclearfoxes.data.db.converters

import androidx.room.TypeConverter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

object ArrayListStringsConverter{
    val objectMapper = ObjectMapper()
    @TypeConverter
    fun fromString(value: String):ArrayList<String>{
        return objectMapper.readValue<ArrayList<String>>(value)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String>):String{
        return objectMapper.writeValueAsString(list)
    }

}