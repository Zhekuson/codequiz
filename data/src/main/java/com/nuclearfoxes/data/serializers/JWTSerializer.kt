package com.nuclearfoxes.data.serializers

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.user.JWTWrapper

object JWTSerializer {
    var objectMapper = jacksonObjectMapper()
    fun deserializeJWT(jsonString: String): String{
            return objectMapper.readValue<JWTWrapper>(jsonString).result
    }
}