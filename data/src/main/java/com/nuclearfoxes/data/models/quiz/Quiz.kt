package com.nuclearfoxes.data.models.quiz

import com.fasterxml.jackson.annotation.JsonProperty
import com.nuclearfoxes.data.models.question.Question
import java.io.Serializable

class Quiz (@JsonProperty("id") var id:Int,
            @JsonProperty("quizType") var quizType:Int,
            @JsonProperty("questions")var questions:ArrayList<Question>?,
            @JsonProperty("minutes") var minutes: Int):Serializable{

}