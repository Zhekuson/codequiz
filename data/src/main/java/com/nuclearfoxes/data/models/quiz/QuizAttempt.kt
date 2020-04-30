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
import kotlin.collections.HashSet

data class QuizAttempt(@JsonProperty("id")var id:Int,
                       @JsonProperty("quiz") @JsonSetter(nulls = Nulls.DEFAULT)var quiz:Quiz?,
                       @JsonProperty("userId")var userId: Int,
                       @JsonProperty("startDateTime")
                       @JsonFormat(pattern = "yyyy-MM-d'T'HH:mm:ss")var startDateTime: Date?,
                       @JsonProperty("endDateTime")
                       @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")var endDateTime: Date?,
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
                    if(userQuizAnswer!!.userAnswers!=null) {
                        if (userQuizAnswer!!
                                .userAnswers!!
                                .count{ x ->
                                    rightAnswer.answerText == x.answerText } > 0) {
                            counter++
                        }
                    }
                }
                QuestionType.MULTIPLE_CHOICE.value->{
                    var rightAnswers:List<String> = ArrayList()
                    for (answer in question.answers!!){
                        if(answer.isRight!!){
                            rightAnswers +=(answer.answerText!!)
                        }
                    }
                    if(userQuizAnswer!!.userAnswers != null)
                    {
                        var userAnswers = ArrayList<String>()
                        for (i in 0 until userQuizAnswer!!.userAnswers!!.size){
                            if((userQuizAnswer!!.userAnswers!![i].questionId==question.id)){
                                userAnswers.add(userQuizAnswer!!.userAnswers!![i].answerText!!)
                            }
                        }
                        var removedAnswers = ArrayList<String>()
                        for (i in 0 until userAnswers.size){
                            rightAnswers = rightAnswers.filter{ x->!userAnswers[i].equals(x) }
                            removedAnswers.add(userAnswers[i])
                        }
                        userAnswers.removeAll(removedAnswers)
                        if(rightAnswers.isEmpty() && userAnswers.isEmpty()){
                            counter++
                        }
                    }


                }
                QuestionType.OPEN.value->{
                    if(userQuizAnswer!!.userAnswers != null) {
                        for (userAnswer in userQuizAnswer!!.userAnswers!!){
                            if(userAnswer.questionId == question.id){
                                counter++
                                break
                            }
                        }
                    }
                }
            }
        }
        return counter
    }
}