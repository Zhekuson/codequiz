package com.nuclearfoxes.data.db.converters

object Converters {
    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType:  = object : TypeToken<ArrayList<String?>?>() {}.getType()
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}