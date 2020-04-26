package com.nuclearfoxes.data.models.quiz

import com.fasterxml.jackson.annotation.JsonProperty
import com.nuclearfoxes.data.models.answers.UserQuizAnswer
import java.util.*

data class QuizAttempt(@JsonProperty("Id")var id:Int,
                       @JsonProperty("Quiz")var quiz:Quiz,
                       @JsonProperty("UserId")var userId: Int,
                       @JsonProperty("StartDateTime")var startDateTime: Date,
                       @JsonProperty("EndDateTime")var endDateTime: Date,
                       @JsonProperty("UserQuizAnswer")var userQuizAnswer: UserQuizAnswer)