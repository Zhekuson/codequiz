package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.UnauthorizedException
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.serializers.QuizAttemptSerializer
import com.nuclearfoxes.data.serializers.QuizSerializer
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

object QuizRequests {
    fun getExamQuiz(JWT:String):Quiz{
        val request= Request.Builder()
            .get()
            .addHeader("Bearer", JWT)
            .url(Config.apiAddress+"quiz/exam").build()
        var response = UserRequests.httpClient.newCall(request).execute()
        if(response.code() == 401){
            throw UnauthorizedException()
        }
        else if(response.code()!= 200){
            throw InternalServerErrorException()
        }
        return QuizSerializer.deserializeQuiz(response.body()!!.string())
    }
    fun getRandomQuiz(JWT:String):Quiz{
        val request= Request.Builder()
            .get()
            .addHeader("Bearer", JWT)
            .url(Config.apiAddress+"quiz/random").build()
        var response = UserRequests.httpClient.newCall(request).execute()
        if(response.code() == 401){
            throw UnauthorizedException()
        }
        else if(response.code()!= 200){
            throw InternalServerErrorException()
        }
        return QuizSerializer.deserializeQuiz(response.body()!!.string())
    }
    fun getCustomQuiz():Quiz{

    }
    fun getQuizById():Quiz{

    }
}