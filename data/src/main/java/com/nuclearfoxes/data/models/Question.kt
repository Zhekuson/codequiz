package com.nuclearfoxes.data.models

data class Question(var question: String,
                    var type: QuestionType,
                    var tags: ArrayList<String>,
                    var answer: String)