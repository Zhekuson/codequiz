package com.nuclearfoxes.data.models.answers

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.Serializable

public class UserQuizAnswer(@JsonProperty("userAnswers") var userAnswers: ArrayList<Answer>?):Serializable {
}