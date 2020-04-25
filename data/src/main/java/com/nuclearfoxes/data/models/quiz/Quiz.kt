package com.nuclearfoxes.data.models.quiz

import com.nuclearfoxes.data.models.question.Question

class Quiz (var id:Int,
            var quizType: QuizType,
            var questions:ArrayList<Question>){

}