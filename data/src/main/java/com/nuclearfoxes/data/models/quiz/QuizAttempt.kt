package com.nuclearfoxes.data.models.quiz

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.nuclearfoxes.data.models.answers.UserQuizAnswer
import java.time.LocalDateTime
import java.util.*

data class QuizAttempt(@JsonProperty("id")var id:Int,
                       @JsonProperty("quiz") @JsonSetter(nulls = Nulls.DEFAULT)var quiz:Quiz?,
                       @JsonProperty("userId")var userId: Int,
                       @JsonProperty("startDateTime")var startDateTime: Date?,
                       @JsonProperty("endDateTime")var endDateTime: Date?,
                       @JsonProperty("userQuizAnswer")var userQuizAnswer: UserQuizAnswer?)