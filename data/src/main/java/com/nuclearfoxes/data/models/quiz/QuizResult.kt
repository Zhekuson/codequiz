package com.nuclearfoxes.data.models.quiz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.OffsetDateTime

@Entity
data class QuizResult(
    @PrimaryKey var id:String,
    @ColumnInfo var totalQuestions:Int,
    @ColumnInfo var rightAnswers:Int,
    @ColumnInfo var date:OffsetDateTime) {
}