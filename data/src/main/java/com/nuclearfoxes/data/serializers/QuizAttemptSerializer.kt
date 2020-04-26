package com.nuclearfoxes.data.serializers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.quiz.QuizAttempt

object QuizAttemptSerializer {
    var objectMapper = jacksonObjectMapper()
    fun serializeQuizAttempt(quizAttempt: QuizAttempt):String{
        return objectMapper.writeValueAsString(quizAttempt)
    }
    fun deserializeQuizAttempt(jsonString: String): QuizAttempt {
        return objectMapper.readValue<QuizAttempt>(jsonString)
    }
}