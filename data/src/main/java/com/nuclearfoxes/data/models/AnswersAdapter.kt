package com.nuclearfoxes.data.models

import java.lang.StringBuilder

object AnswersAdapter {
    fun multipleToString(collection: ArrayList<Int>):String{
        var strBuilder = StringBuilder()
        for (ans in collection){
            strBuilder.append(ans.toString())
        }
        return strBuilder.toString()
    }
    fun singleToString(c:Int):String{
        return c.toString()
    }
}