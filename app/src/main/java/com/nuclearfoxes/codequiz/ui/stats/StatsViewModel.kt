package com.nuclearfoxes.codequiz.ui.stats

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nuclearfoxes.data.api.StatsRequests
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StatsViewModel():ViewModel() {
    lateinit var listQuizAttempt: ArrayList<QuizAttempt>
    interface LoadingFinishedListener{
        fun onFinish()
    }
    suspend fun getQuizAttempts(context: Context, listener:LoadingFinishedListener ){
        listQuizAttempt = StatsRequests.getAllQuizAttempts(
            context.getSharedPreferences("MAIN",
                Context.MODE_PRIVATE).getString("EMAIL",""),
            context.getSharedPreferences("MAIN",
                Context.MODE_PRIVATE).getString("JWT",""))
        listener.onFinish()
    }
}