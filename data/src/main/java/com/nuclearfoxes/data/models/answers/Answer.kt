package com.nuclearfoxes.data.models.answers

import com.fasterxml.jackson.annotation.JsonProperty
import com.nuclearfoxes.data.models.question.Question
import java.io.Serializable

class Answer(@JsonProperty("id")var id:Int?,
             @JsonProperty("answerText")var answerText:String?,
             @JsonProperty("isRight")var isRight:Boolean?,
             @JsonProperty("questionId")var questionId:Int?):Serializable