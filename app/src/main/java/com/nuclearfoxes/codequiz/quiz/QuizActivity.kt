package com.nuclearfoxes.codequiz.quiz

import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType

import kotlinx.android.synthetic.main.activity_quiz.*

class QuizActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenWindow()
        setContentView(R.layout.activity_quiz)
    }

    override fun onStart() {
        var questions: ArrayList<Question> = ArrayList()
        questions.add(Question("d",
            "ef",
            QuestionType.MULTIPLE_CHOICE,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))
        question_view_pager.adapter =
            QuestionFragmentPagerAdapter(this,supportFragmentManager,questions)
        super.onStart()
    }
    fun setFullScreenWindow(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}
