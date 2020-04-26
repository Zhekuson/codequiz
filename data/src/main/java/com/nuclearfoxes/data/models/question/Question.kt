package com.nuclearfoxes.data.models.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.nuclearfoxes.data.models.answers.Answer
import com.nuclearfoxes.data.models.tags.Tag
import java.io.Serializable



public data class Question(
    @JsonProperty("ID")var id:String,
    @JsonProperty("QuestionText")var questionText: String,
    @JsonProperty("Type")var type: QuestionType,
    @JsonProperty("Tags")var tags: ArrayList<Tag>,
    @JsonProperty("Answers")var answers:ArrayList<Answer>
):Serializable