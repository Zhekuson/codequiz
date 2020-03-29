package com.nuclearfoxes.data.models.question

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
public data class Question(
    @PrimaryKey var id:String,
    @ColumnInfo var question: String,
    @ColumnInfo var type: QuestionType,
    @ColumnInfo var tags: ArrayList<String>,
    @ColumnInfo var rightAnswer: ArrayList<String>,
    @ColumnInfo var answers:ArrayList<String>
):Serializable