package com.nuclearfoxes.data.models.question

import java.io.Serializable

public enum class QuestionType(var value: Int):Serializable{
    NONE(0),
    SINGLE_CHOICE(1),
    MULTIPLE_CHOICE(2),
    OPEN(3)
}