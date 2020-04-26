package com.nuclearfoxes.data.models.user

import com.fasterxml.jackson.annotation.JsonProperty

class JWTWrapper(@JsonProperty("accessToken")var result:String) {
    fun getToken():String{
        return result
    }
}