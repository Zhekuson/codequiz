package com.nuclearfoxes.data.models.quiz

enum class QuizType(var value:Int) {
    EXAM(2),
    ALL_RANDOM(1),
    CUSTOM(3);
    fun toInt():Int{
        return value
    }
}