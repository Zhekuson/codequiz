package com.nuclearfoxes.codequiz.ui.tests.loading

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.data.api.QuizRequests
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.tags.Tag
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class QuizLoadingActivity : AppCompatActivity() {

    lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_loading)
        sharedPreferences = this.getSharedPreferences("MAIN",Context.MODE_PRIVATE)
    }

    override fun onStart() {
        super.onStart()
        getMode()
    }
    fun getMode(){
        var mode = intent.getStringExtra("MODE")
        var intentNext = Intent(this, QuizActivity::class.java)
        var quiz:Quiz
        GlobalScope.launch {
            when (mode) {
                "CUSTOM" -> {
                    val minutesCount = intent.getIntExtra("MINUTES", 1)
                    val questionsCount = intent.getIntExtra("QUESTIONS_COUNT", 1)
                    val tags: ArrayList<Tag> = intent.getSerializableExtra("TAGS") as ArrayList<Tag>
                    quiz = QuizRequests.getCustomQuiz(
                        sharedPreferences.getString("JWT", "-")!!,
                        tags, questionsCount, minutesCount
                    )
                    intentNext.putExtra("TIME_MS", quiz.minutes * 60000L)
                }
                "RANDOM" -> {

                    intentNext.putExtra("TIME_MS", 600000L)
                    quiz = QuizRequests.getRandomQuiz(sharedPreferences.getString("JWT", "-")!!)
                }
                "EXAM" -> {
                    intentNext.putExtra("TIME_MS", 3600000L)
                    quiz = QuizRequests.getExamQuiz(sharedPreferences.getString("JWT", "-")!!)
                }
                "BY_ID" -> {
                    quiz = QuizRequests.getQuizById(
                        sharedPreferences.getString("JWT", "-")!!,
                        intent.getIntExtra("ID", 1)
                    )
                    intentNext.putExtra("TIME_MS", quiz.minutes * 60000L)
                }
                else -> {
                    throw UnableStartQuizException()
                }
            }
            intentNext.putExtra("QUIZ", quiz)
            startActivity(intentNext)
        }
    }

}
