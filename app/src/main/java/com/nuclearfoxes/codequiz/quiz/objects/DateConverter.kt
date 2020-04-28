package com.nuclearfoxes.codequiz.quiz.objects

import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

object DateConverter {
    fun fromLocalDateToDate(localDateTime: LocalDateTime): Date {
        return Date.from( localDateTime.atZone( ZoneId.systemDefault()).toInstant());
    }

}