package com.nuclearfoxes.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.nuclearfoxes.data.models.question.Question

@Dao
interface QuestionDao {

    @Query("SELECT * FROM Question")
    fun getAll():MutableList<Question>
}