package com.nuclearfoxes.data.api

import org.junit.jupiter.api.Test

internal class StatsRequestsTest {

    @Test
    fun getAllQuizAttempts() {
       var list = StatsRequests.getAllQuizAttempts("edplyusch@edu.hse.ru","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImVkcGx5dXNjaEBlZHUuaHNlLnJ1IiwibmJmIjoxNTg3ODA0MjcwLCJleHAiOjE2MTkzNDAyNzAsImlzcyI6IlNlcnZlciIsImF1ZCI6IkNsaWVudCJ9._HnX8xj8Poq_kz91qan2xOK3E3UV_55lWgOCt_0LItc")
    }
}