package com.nuclearfoxes.data.serializers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nuclearfoxes.data.models.quiz.Quiz

object QuizSerializer {
    var objectMapper = jacksonObjectMapper()
    fun serializeQuiz(quiz: Quiz):String{
        return objectMapper.writeValueAsString(quiz)
    }
    fun deserializeQuiz(jsonString: String):Quiz{
        return objectMapper.readValue<Quiz>(jsonString)
    }
}