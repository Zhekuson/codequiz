package com.nuclearfoxes.data.models.quiz

import com.fasterxml.jackson.annotation.JsonProperty
import com.nuclearfoxes.data.models.question.Question
import java.io.Serializable

class Quiz (@JsonProperty("ID") var id:Int,
            @JsonProperty("QuizType") var quizType:Int,
            @JsonProperty("Questions")var questions:ArrayList<Question>,
            @JsonProperty("Minutes") var minutes: Int):Serializable{

}