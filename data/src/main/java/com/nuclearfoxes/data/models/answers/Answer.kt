package com.nuclearfoxes.data.models.answers

import com.nuclearfoxes.data.models.question.Question

class Answer(var id:Int,
             var AnswerText:String,
             var isRight:Boolean,
             var questionId:Int)