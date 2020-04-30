package com.nuclearfoxes.codequiz.ui.stats

import android.content.Context
import androidx.lifecycle.ViewModel
import com.nuclearfoxes.data.api.StatsRequests
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StatsViewModel(context: Context):ViewModel() {
    lateinit var listQuizAttempt: ArrayList<QuizAttempt>
    suspend fun getQuizAttempts(context: Context){
        listQuizAttempt = StatsRequests.getAllQuizAttempts(
            context.getSharedPreferences("MAIN",
                Context.MODE_PRIVATE).getString("JWT",""),
            context.getSharedPreferences("MAIN",
                Context.MODE_PRIVATE).getString("EMAIL",""))
    }
}