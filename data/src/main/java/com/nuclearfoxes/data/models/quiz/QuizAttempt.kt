package com.nuclearfoxes.data.models.quiz

import com.nuclearfoxes.data.models.answers.UserQuizAnswer
import java.util.*

data class QuizAttempt(var id:Int,
                       var quiz:Quiz,
                       var userId: Int,
                       var startDateTime: Date,
                       var endDateTime: Date,
                       var UserQuizAnswer: UserQuizAnswer)