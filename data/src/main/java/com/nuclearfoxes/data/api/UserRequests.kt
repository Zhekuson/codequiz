package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.VerificationException
import com.nuclearfoxes.data.serializers.JWTSerializer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.lang.Exception
import java.lang.NumberFormatException

object UserRequests {
    @Volatile
    var httpClient = OkHttpClient()
    /**
     * returns session id
     */
    fun sendVerificationEmail(email: String):Int{
        val request= Request.Builder()
            .get()
            .url(Config.apiAddress+"auth/verification?email=${email}").build()
        var responce = httpClient.newCall(request).execute()
        try {
            var body = responce.body()
            return body!!.string().toInt()
        }catch (e:NumberFormatException){
            throw InternalServerErrorException()
        }
        catch (e:Exception){
            throw InternalServerErrorException()
        }
    }

    /**
     * returns JWT
     */
    fun verifyEmail(sessionId:Int, code:Int, email: String):String{
        val request= Request.Builder()
            .get()
            .url(Config.apiAddress+"verification?email=${email}" +
                    "&sessionId=${sessionId}&code=${code}").build()
        var response = httpClient.newCall(request).execute()
        if (response.code() == 500){
            throw InternalServerErrorException()
        }else if(response.code() == 401){
            throw VerificationException()
        }
        try {
            return JWTSerializer.deserializeJWT(response.body()!!.string())
        }catch (e:Exception){
            throw VerificationException()
        }
    }
}
