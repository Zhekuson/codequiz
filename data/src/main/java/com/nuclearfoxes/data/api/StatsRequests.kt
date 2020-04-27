package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.UnauthorizedException
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import com.nuclearfoxes.data.serializers.QuizAttemptSerializer
import com.nuclearfoxes.data.serializers.QuizSerializer
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import java.lang.Exception

object StatsRequests {
    fun GetAllQuizAttempts(email:String, JWT:String):ArrayList<QuizAttempt>{
        val request= Request.Builder()
            .get().addHeader("Bearer", JWT)
            .url(Config.apiAddress+"stats?email=${email}").build()
        var response = UserRequests.httpClient.newCall(request).execute()
        if (response.code() == 401){
            throw UnauthorizedException()
        }
        if(response.code() == 500){
            throw InternalServerErrorException()
        }
        try {
            return QuizAttemptSerializer.deserializeQuizAttemptsList(response.body()!!.string())
        }catch (e:Exception){
            throw InternalServerErrorException()
        }
    }
    fun putQuizAttempt(quizAttempt: QuizAttempt, email:String, JWT:String) {
        val request= Request.Builder()
            .put(RequestBody.create(
                MediaType.parse(Config.contentType),
                QuizAttemptSerializer.serializeQuizAttempt(quizAttempt)))
            .addHeader("Bearer", JWT)
            .url(Config.apiAddress+"quiz/answer").build()
        var response = UserRequests.httpClient.newCall(request).execute()
        if(response.code() != 200){
            throw InternalServerErrorException()
        }
    }
}