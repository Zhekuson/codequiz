package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.QuizNotFoundException
import com.nuclearfoxes.data.exceptions.UnauthorizedException
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.tags.Tag
import com.nuclearfoxes.data.serializers.QuizAttemptSerializer
import com.nuclearfoxes.data.serializers.QuizSerializer
import com.nuclearfoxes.data.serializers.TagSerializer
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody

object QuizRequests {
    fun getExamQuiz(JWT:String):Quiz{
        val request= Request.Builder()
            .get()
            .addHeader("Authorization","Bearer "+ JWT)
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
            .addHeader("Authorization","Bearer "+ JWT)
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
    fun getCustomQuiz(JWT:String, tags:ArrayList<Tag>, questionsCount:Int, minutesCount:Int):Quiz{
        val request= Request.Builder()
            .post(
                RequestBody.create(
                MediaType.parse(Config.contentType),
                TagSerializer.serializeTagList(tags)))
            .addHeader("Authorization","Bearer "+ JWT)
            .url(Config.apiAddress+"quiz/custom?questionsCount=" +
                    "${questionsCount}&minutesCount${minutesCount}").build()
        var response = UserRequests.httpClient.newCall(request).execute()
        if(response.code() == 401){
            throw UnauthorizedException()
        }
        else if(response.code()!= 200){
            throw InternalServerErrorException()
        }
        return QuizSerializer.deserializeQuiz(response.body()!!.string())
    }
    fun getQuizById(JWT:String, id:Int):Quiz{
        val request= Request.Builder()
            .get()
            .addHeader("Authorization","Bearer "+ JWT)
            .url(Config.apiAddress+"quiz/${id}").build()
        var response = UserRequests.httpClient.newCall(request).execute()
        if(response.code() == 401){
            throw UnauthorizedException()
        }else if(response.code()== 404){
            throw QuizNotFoundException()
        }
        else if(response.code()!= 200){
            throw InternalServerErrorException()
        }
        return QuizSerializer.deserializeQuiz(response.body()!!.string())
    }
}