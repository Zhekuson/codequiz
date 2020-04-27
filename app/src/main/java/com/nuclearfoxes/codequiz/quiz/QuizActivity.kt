package com.nuclearfoxes.codequiz.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.adapters.QuestionFragmentPagerAdapter
import com.nuclearfoxes.codequiz.quiz.objects.TimeConverter
import com.nuclearfoxes.codequiz.result.ResultActivity
import com.nuclearfoxes.data.models.question.Question
import com.nuclearfoxes.data.models.question.QuestionType

import kotlinx.android.synthetic.main.activity_quiz.*
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity() {
    lateinit var questions: ArrayList<Question>
    lateinit var mAdapter: QuestionFragmentPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenWindow()
        setContentView(R.layout.activity_quiz)

        //questions = intent.getSerializableExtra("QUESTIONS") as ArrayList<Question>
        val timer = object: CountDownTimer(intent.getLongExtra("TIME_MS", 300000), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time_textView.text = TimeConverter.timeInMsToString(millisUntilFinished)
            }

            override fun onFinish() {
            }
        }
        timer.start()
    }


    override fun onStart() {

        setupAdapters()

        //time_textView.text = timer.
        super.onStart()
    }
    fun setFullScreenWindow(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    fun setupAdapters(){

    }
}
