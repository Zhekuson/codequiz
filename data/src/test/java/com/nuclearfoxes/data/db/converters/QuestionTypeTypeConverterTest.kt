package com.nuclearfoxes.data.db.converters

import com.nuclearfoxes.data.models.QuestionType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuestionTypeTypeConverterTest {

    @Test
    fun stringToQuestionTypeTest() {
        assertEquals(QuestionTypeTypeConverter
            .stringToQuestionType("OPEN"),QuestionType.OPEN)
    }

    @Test
    fun questionTypeToStringTest() {
        assertEquals(QuestionTypeTypeConverter
            .questionTypeToString(QuestionType.MULTIPLE_CHOICE),"MULTIPLE_CHOICE")
    }
}