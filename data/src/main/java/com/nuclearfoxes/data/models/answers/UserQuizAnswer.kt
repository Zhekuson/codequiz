package com.nuclearfoxes.data.models.answers

import com.fasterxml.jackson.annotation.JsonProperty

public class UserQuizAnswer(@JsonProperty("userAnswers") var userAnswers: ArrayList<Answer>?) {
}