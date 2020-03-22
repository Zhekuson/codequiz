package com.nuclearfoxes.codequiz.quiz

import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.adapters.QuestionFragmentPagerAdapter
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType

import kotlinx.android.synthetic.main.activity_quiz.*
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity() {
    //TODO add timing in minutes
    val timer = object: CountDownTimer(301000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            time_textView.text = TimeConverter.timeInMsToString(millisUntilFinished)
        }

        override fun onFinish() {
            //var intent = Intent(this@QuizActivity)
            //startActivity()
            //TODO add start activity
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenWindow()
        setContentView(R.layout.activity_quiz)
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
        var questions: ArrayList<Question> = ArrayList()
        questions.add(Question("d",
            "ef",
            QuestionType.MULTIPLE_CHOICE,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))
        questions.add(Question("vd",
            "ef",
            QuestionType.SINGLE_CHOICE,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))
        questions.add(Question("vd",
            "ef",
            QuestionType.OPEN,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))
        question_view_pager.adapter =
            QuestionFragmentPagerAdapter(
                this,
                supportFragmentManager,
                questions
            )

    }
}
