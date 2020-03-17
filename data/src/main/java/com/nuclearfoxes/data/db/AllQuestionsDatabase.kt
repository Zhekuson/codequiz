package com.nuclearfoxes.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.nuclearfoxes.data.models.Question

class AllQuestionsDatabase {@Database(entities = arrayOf(Question::class),
    exportSchema = false,
    /*views = arrayOf(Marker::class),*/
    version = 1)

abstract class AllMarkersDatabase(): RoomDatabase() {

    companion object{
        @Volatile
        private var INSTANCE: AllMarkersDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): AllMarkersDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AllMarkersDatabase::class.java,
                    "MARKERWRAPPER"
                )
                    .addCallback(MarkerDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }

    private class MarkerDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.allMarkersDao())
                }
            }
        }

        suspend fun populateDatabase(allMarkersDao: AllMarkersDao ) {
            // Delete all content here.
            // wordDao.deleteAll()

            // Add sample words.
            //   var marker = Marker("1","Copic","Ciao",
            //      "BV0009","Copic Blue Violet","Blue Violet","BV" ,"#00FF00")
            //  allMarkersDao.insertMarker(MarkerWrapper.markerToWrapper(marker,false,false,false))
            // allMarkersDao.insertMarker(MarkerWrapper.markerToWrapper(marker,true,true,true))
            //base = MarkerRequests.getAllMarkersBase()
            // allMarkersDao.insertMarker()



        }
    }
}
}