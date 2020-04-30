package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.UnauthorizedException
import com.nuclearfoxes.data.models.tags.Tag
import com.nuclearfoxes.data.models.tags.TagCountPair
import com.nuclearfoxes.data.serializers.QuizAttemptSerializer
import com.nuclearfoxes.data.serializers.TagSerializer
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import java.lang.Exception
import java.net.SocketTimeoutException

object TagsRequests {
    fun getTagCountPairs(JWT:String):ArrayList<TagCountPair>{
        val request= Request.Builder()
            .get().addHeader("Authorization","Bearer "+ JWT)
            .url(Config.apiAddress+"tags").build()
        try {
        var response = UserRequests.httpClient.newCall(request).execute()
            return TagSerializer.deserializeTagCountPairs(response.body()!!.string())
        }catch (e:SocketTimeoutException){
            throw e
        }
        catch (e: Exception){
            throw InternalServerErrorException()
        }
    }
    fun getMaxCountQuestions(JWT: String, tags:ArrayList<Tag>):Int{
        val body = TagSerializer.serializeTagList(tags)
        val request= Request.Builder()
            .post(
                RequestBody.create(
                    MediaType.parse(Config.contentType),body
                )).addHeader("Authorization","Bearer "+ JWT)
            .url(Config.apiAddress+"tags/count").build()
        try {
            var response = UserRequests.httpClient.newCall(request).execute()
            return response.body()!!.string().toInt()
        }catch (e:SocketTimeoutException){
            throw e
        }
        catch (e: Exception){
            throw InternalServerErrorException()
        }
    }
}