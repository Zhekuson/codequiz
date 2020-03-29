package com.nuclearfoxes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nuclearfoxes.data.db.converters.QuizTypeTypeConverter
import com.nuclearfoxes.data.models.quiz.QuizResult
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(QuizResult::class),version = 1,exportSchema = false)
@TypeConverters(QuizTypeTypeConverter::class)
abstract class PassedQuizDatabase:RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE: PassedQuizDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): PassedQuizDatabase{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PassedQuizDatabase::class.java,
                    "QUIZRESULT"
                )
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

}