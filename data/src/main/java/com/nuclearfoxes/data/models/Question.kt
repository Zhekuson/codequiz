package com.nuclearfoxes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity
public data class Question(
    @PrimaryKey var id:String,
    @ColumnInfo var question: String,
    @ColumnInfo var type: QuestionType,
    @ColumnInfo var tags: ArrayList<String>,
    @ColumnInfo var rightAnswer: ArrayList<String>,
    @ColumnInfo var answers:ArrayList<String>
){

}