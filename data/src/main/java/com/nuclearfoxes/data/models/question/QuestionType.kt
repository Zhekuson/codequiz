package com.nuclearfoxes.data.models.question

public enum class QuestionType(var value: Int) {
    OPEN(3),
    SINGLE_CHOICE(1),
    MULTIPLE_CHOICE(2),
}