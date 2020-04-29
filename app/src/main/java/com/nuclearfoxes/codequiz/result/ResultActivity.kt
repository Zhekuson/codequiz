package com.nuclearfoxes.codequiz.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nuclearfoxes.codequiz.MainActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.result.adapters.ResultAdapter
import com.nuclearfoxes.data.models.question.Question
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    lateinit var mAdapter: ResultAdapter
    lateinit var quizAttempt: QuizAttempt
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        this.getSupportActionBar()?.hide()
        quizAttempt = intent
            .getSerializableExtra("QUIZ_ATTEMPT") as QuizAttempt
        mAdapter = ResultAdapter(quizAttempt,this)
        result_list_view.adapter = mAdapter
        setupOnClickListeners()
    }
    fun setupOnClickListeners() {
        skip_results_button.setOnClickListener {
            var resultIntent = Intent(this,MainActivity::class.java)
            resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(resultIntent)
        }
        restart_button.setOnClickListener {
            var restartIntent = Intent(this,QuizActivity::class.java)
            restartIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            restartIntent.putExtra("QUIZ", quizAttempt.quiz)
            startActivity(restartIntent)
        }
    }
    override fun onStart() {
        result_score_text_view.text = "${quizAttempt.countRightAnswers()}/${quizAttempt.quiz!!.questions!!.size}"
        super.onStart()
    }

}
