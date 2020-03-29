package com.nuclearfoxes.data.db.converters

import androidx.room.TypeConverter

import com.nuclearfoxes.data.models.quiz.QuizType

object QuizTypeTypeConverter {
    @TypeConverter
    fun stringToQuizType(enumName: String): QuizType = QuizType.valueOf(enumName)

    @TypeConverter
    fun quizTypeToString(enumType: QuizType) = enumType.name
}