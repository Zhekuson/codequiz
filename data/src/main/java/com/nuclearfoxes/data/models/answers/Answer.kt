package com.nuclearfoxes.data.models.answers

import com.fasterxml.jackson.annotation.JsonProperty
import com.nuclearfoxes.data.models.question.Question

class Answer(@JsonProperty("Id")var id:Int,
             @JsonProperty("AnswerText")var answerText:String,
             @JsonProperty("IsRight")var isRight:Boolean,
             @JsonProperty("QuestionId")var questionId:Int)