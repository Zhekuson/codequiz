package com.nuclearfoxes.data.serializers

import com.nuclearfoxes.data.models.question.Question
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.quiz.QuizType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class QuizSerializerTest {

    @Test
    fun serializeQuizTest() {
        var questions = ArrayList<Question>()
        var quiz:Quiz = Quiz(1,QuizType.CUSTOM.value,questions, 1)
        var result = QuizSerializer.serializeQuiz(quiz)
    }

    @Test
    fun deserializeQuiz() {
        var questions = ArrayList<Question>()
        var quiz:Quiz = Quiz(1,QuizType.CUSTOM.value,questions, 1)
        var result = QuizSerializer.serializeQuiz(quiz)
        var resQuiz = QuizSerializer.deserializeQuiz(result)
    }
}