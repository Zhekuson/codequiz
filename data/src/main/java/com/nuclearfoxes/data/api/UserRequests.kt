package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.serializers.JWTSerializer
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

object UserRequests {
    @Volatile
    var httpClient = OkHttpClient()
    /**
     * returns session id
     */
    fun sendVerificationEmail(email: String):Int{
        val request= Request.Builder()
            .get()
            .url(Config.apiAddress+"verification?email=${email}").build()
        var responce = httpClient.newCall(request).execute()
        return responce.body().toString().toInt()
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
        return JWTSerializer.deserializeJWT(response.body().toString())
    }
}
