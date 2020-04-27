package com.nuclearfoxes.data.api

import com.nuclearfoxes.data.exceptions.VerificationException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

internal class UserRequestsTest {

    @Test
    fun sendVerificationEmail() {
       // var sessionId = UserRequests.sendVerificationEmail("zhekuson@gmail.com")
        try {
            UserRequests.verifyEmail(26, 84287, "zhekuson@gmail.com")
        }catch (e:VerificationException){

        }

    }

    @Test
    fun verifyEmail() {
    }
}