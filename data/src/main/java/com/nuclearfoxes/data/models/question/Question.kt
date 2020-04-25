package com.nuclearfoxes.data.models.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nuclearfoxes.data.models.answers.Answer
import com.nuclearfoxes.data.models.tags.Tag
import java.io.Serializable



public data class Question(
    var id:String,
    var questionText: String,
    var type: QuestionType,
    var tags: ArrayList<Tag>,
    var answers:ArrayList<Answer>
):Serializable