package com.nuclearfoxes.data.models.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import com.nuclearfoxes.data.models.answers.Answer
import com.nuclearfoxes.data.models.tags.Tag
import java.io.Serializable



public data class Question(
    @JsonProperty("id")var id:String?,
    @JsonProperty("questionText")var questionText: String?,
    @JsonProperty("type")var type: QuestionType?,
    @JsonProperty("tags")var tags: ArrayList<Tag>?,
    @JsonProperty("answers")var answers:ArrayList<Answer>?
):Serializable