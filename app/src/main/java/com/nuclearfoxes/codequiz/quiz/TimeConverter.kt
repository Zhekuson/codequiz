package com.nuclearfoxes.codequiz.quiz

object TimeConverter {
    fun timeInMsToString(timeMs:Long):String{
        val minutes = timeMs / (1000*60)
        val seconds = timeMs / 1000 % 60
        var minutesStr:String = timeToStr(minutes)
        var secondsStr:String = timeToStr(seconds)
        return "$minutesStr:$secondsStr"
    }
    private fun timeToStr(time:Long):String{
        if (time < 10){
            return "0$time"
        }
        return "$time"
    }
}