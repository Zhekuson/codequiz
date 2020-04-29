package com.nuclearfoxes.data.models.quiz

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonSetter
import com.fasterxml.jackson.annotation.Nulls
import com.fasterxml.jackson.databind.util.ISO8601DateFormat
import com.nuclearfoxes.data.models.answers.Answer
import com.nuclearfoxes.data.models.answers.UserQuizAnswer
import com.nuclearfoxes.data.models.question.QuestionType
import java.io.Serializable
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class QuizAttempt(@JsonProperty("id")var id:Int,
                       @JsonProperty("quiz") @JsonSetter(nulls = Nulls.DEFAULT)var quiz:Quiz?,
                       @JsonProperty("userId")var userId: Int,
                       @JsonProperty("startDateTime")
                       @JsonFormat(pattern = "yyyy-MM-d'T'HH:mm:ss.SSS")var startDateTime: Date?,
                       @JsonProperty("endDateTime")
                       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")var endDateTime: Date?,
                       @JsonProperty("userQuizAnswer")var userQuizAnswer: UserQuizAnswer?):Serializable{
    companion object {
        private val ISO_8601_DATE_TIME: DateFormat =
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ")
    }
    fun countRightAnswers():Int{
        var counter = 0
        var questions = quiz!!.questions
        for (question in questions!!){
            when(question.type){
                QuestionType.SINGLE_CHOICE.value->{
                    var rightAnswer:Answer = Answer(0,"",true,question.id)
                    for (answer in question.answers!!){
                        if(answer.isRight!!){
                            rightAnswer = answer
                            break
                        }
                    }
                    if(userQuizAnswer!!.userAnswers!!.contains(rightAnswer)){
                        counter++
                    }
                }
                QuestionType.MULTIPLE_CHOICE.value->{
                    var rightAnswers:ArrayList<Answer> = ArrayList()
                    for (answer in question.answers!!){
                        if(answer.isRight!!){
                            rightAnswers.add(answer)
                        }
                    }
                    if(userQuizAnswer!!.userAnswers!!.containsAll(rightAnswers)
                        && userQuizAnswer!!.userAnswers!!.count{it.questionId==question.id}
                        == rightAnswers.size){
                        counter++
                    }
                }
                QuestionType.OPEN.value->{
                    if(userQuizAnswer!!.userAnswers!=null) {
                        if (userQuizAnswer!!.userAnswers!!.contains(question.answers!![0])){
                            counter++
                        }
                    }
                }
            }
        }
        return counter
    }
}