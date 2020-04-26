package com.nuclearfoxes.data.models.answers

import com.fasterxml.jackson.annotation.JsonProperty

class UserQuizAnswer(@JsonProperty("UserAnswers") var userAnswers: ArrayList<Answer>) {
}