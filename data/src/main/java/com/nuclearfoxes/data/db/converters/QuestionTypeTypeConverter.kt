package com.nuclearfoxes.data.db.converters

import androidx.room.TypeConverter
import com.nuclearfoxes.data.models.QuestionType

object QuestionTypeTypeConverter {
        @TypeConverter
        fun stringToQuestionType(enumName: String): QuestionType = QuestionType.valueOf(enumName)

        @TypeConverter
        fun questionTypeToString(enumType: QuestionType) = enumType.name
}

