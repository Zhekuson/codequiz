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
import java.net.SocketTimeoutException

object StatsRequests {
    fun getAllQuizAttempts(email:String, JWT:String):ArrayList<QuizAttempt>{
        val request= Request.Builder()
            .get().addHeader("Authorization","Bearer "+ JWT)
            .url(Config.apiAddress+"stats/?email=${email}").build()
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
        var body = QuizAttemptSerializer.serializeQuizAttempt(quizAttempt)
        val request= Request.Builder()
            .post(RequestBody.create(
                MediaType.parse(Config.contentType),body
                ))
            .addHeader("Authorization","Bearer "+ JWT)
            .url(Config.apiAddress+"quiz/answer?email=${email}").build()
        try {
            var response = UserRequests.httpClient.newCall(request).execute()
            if(response.code() == 500){
                throw InternalServerErrorException()
            }
        }catch (e:SocketTimeoutException){

        }

    }
}