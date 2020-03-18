package com.nuclearfoxes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nuclearfoxes.data.db.converters.QuestionTypeTypeConverter
import com.nuclearfoxes.data.db.converters.TagsConverter
import com.nuclearfoxes.data.models.Question
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(Question::class),
    exportSchema = false,
    version = 1)
@TypeConverters(QuestionTypeTypeConverter::class, TagsConverter::class)
abstract class AllQuestionsDatabase:RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE: AllQuestionsDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AllQuestionsDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllQuestionsDatabase::class.java,
                    "QUESTION"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}
