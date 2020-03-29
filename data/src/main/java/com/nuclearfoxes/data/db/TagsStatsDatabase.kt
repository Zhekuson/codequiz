package com.nuclearfoxes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import com.nuclearfoxes.data.models.tags.TagStats
import kotlinx.coroutines.CoroutineScope

@Database(version = 1,exportSchema = false,entities = arrayOf(TagStats::class))
abstract class TagsStatsDatabase:RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE: TagsStatsDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): TagsStatsDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TagsStatsDatabase::class.java,
                    "TAGSTATS"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}