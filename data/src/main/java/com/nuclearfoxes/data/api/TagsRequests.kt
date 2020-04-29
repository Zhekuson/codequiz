package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.UnauthorizedException
import com.nuclearfoxes.data.models.tags.TagCountPair
import com.nuclearfoxes.data.serializers.QuizAttemptSerializer
import com.nuclearfoxes.data.serializers.TagSerializer
import okhttp3.Request
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
}