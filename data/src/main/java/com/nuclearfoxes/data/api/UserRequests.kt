package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.MailException
import com.nuclearfoxes.data.exceptions.TooMuchAttemptsException
import com.nuclearfoxes.data.exceptions.VerificationException
import com.nuclearfoxes.data.serializers.JWTSerializer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.lang.Exception
import java.lang.NumberFormatException
import java.net.SocketTimeoutException

object UserRequests {
    var httpClient = OkHttpClient()
    /**
     * returns session id
     */
    fun sendVerificationEmail(email: String):Int{
        val request= Request.Builder()
            .get()
            .url(Config.apiAddress+"auth/verification?email=${email}").build()
        try {
        var responce = httpClient.newCall(request).execute()
            var body = responce.body()
            return body!!.string().toInt()
        }catch (e:NumberFormatException){
            throw MailException()
        }
        catch (e:SocketTimeoutException){
            throw e
        }
        catch (e:Exception){
            throw e
        }
    }

    /**
     * returns JWT
     */
    fun verifyEmail(sessionId:Int, code:Int, email: String):String{
        val request= Request.Builder()
            .get()
            .url(Config.apiAddress+"auth/verify?email=${email}" +
                    "&sessionId=${sessionId}&code=${code}").build()
        var response = httpClient.newCall(request).execute()
        var json = response.body()!!.string()
        if (response.code() == 500){
            throw InternalServerErrorException()
        }else if (response.code() == 401){
            throw VerificationException()
        }else if (response.code() == 403){
            throw TooMuchAttemptsException()
        }
        return JWTSerializer.deserializeJWT(json)
    }
}
