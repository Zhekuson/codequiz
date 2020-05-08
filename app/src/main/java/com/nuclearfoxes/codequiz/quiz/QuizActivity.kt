package com.nuclearfoxes.codequiz.quiz

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.view.ViewParent
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.adapters.QuestionFragmentPagerAdapter
import com.nuclearfoxes.codequiz.quiz.objects.TimeConverter
import com.nuclearfoxes.codequiz.result.ResultActivity
import com.nuclearfoxes.data.api.StatsRequests
import com.nuclearfoxes.data.models.answers.UserQuizAnswer
import com.nuclearfoxes.data.models.question.Question
import com.nuclearfoxes.data.models.question.QuestionType
import com.nuclearfoxes.data.models.quiz.Quiz
import com.nuclearfoxes.data.models.quiz.QuizAttempt

import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.Serializable
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class QuizActivity : AppCompatActivity(),ConfirmFinishFragment.ConfirmationListener, ViewPager.OnPageChangeListener{
    lateinit var mAdapter: QuestionFragmentPagerAdapter
    lateinit var quiz: Quiz
    lateinit var quizAttempt: QuizAttempt
    lateinit var sharedPreferences: SharedPreferences
    lateinit var timer1:CountDownTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFullScreenWindow()
        sharedPreferences = this.getSharedPreferences("MAIN", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("DARK_THEME",false)){
            setTheme(R.style.DarkAppTheme)
        }
        setContentView(R.layout.activity_quiz)


        quiz = intent.getSerializableExtra("QUIZ") as Quiz
        quiz_id_text_view.text = quiz.id.toString()
        quizAttempt = QuizAttempt(0,quiz,0, Date(),Date(),null)
        //TIMER
        timer1 = object: CountDownTimer(intent.getLongExtra("TIME_MS", 300000), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                time_textView.text = TimeConverter.timeInMsToString(millisUntilFinished)
            }
            override fun onFinish() {
               finishQuiz()
            }
        }
        question_id_textView.text = "#${quiz.questions!![0].id}"
        question_number_text_view.text = "${1}/${quiz.questions!!.size}"
        finish_attempt_button.setOnClickListener {
            ConfirmFinishFragment().show(supportFragmentManager,"TAG")
        }
        timer1.start()
    }

    fun finishQuiz(){
        quizAttempt.userQuizAnswer = UserQuizAnswer(ArrayList())
        for (fragment in mAdapter.fragments){
            val qFragment = fragment as QuestionFragment
            when(qFragment.question.type){
                QuestionType.MULTIPLE_CHOICE.value->{
                    var bools = qFragment.savedInfo.getBooleanArray("ANSWER")
                    if(bools!=null) {
                        for (i in 0 until bools.size) {
                            if (bools[i]) {
                                quizAttempt.userQuizAnswer!!.userAnswers!!
                                    .add(qFragment.question.answers!![i])
                            }
                        }
                    }
                }
                QuestionType.OPEN.value->{
                if(qFragment.question.answers != null) {
                    if (qFragment.question.answers!![0].answerText ==
                        qFragment.savedInfo.getString("ANSWER", "?")
                    ) {
                        quizAttempt.userQuizAnswer!!.userAnswers!!.add(qFragment.question.answers!![0])
                    }
                }
                }
                QuestionType.SINGLE_CHOICE.value->{
                    if(qFragment.question.answers != null) {
                        for (answer in qFragment.question.answers!!) {
                            if (qFragment.savedInfo.getString("ANSWER")
                                == answer.answerText
                            ) {
                                quizAttempt.userQuizAnswer!!.userAnswers!!.add(answer)
                                break
                            }
                        }
                    }
                }
                QuestionType.NONE.value->{}
            }
        }
        quizAttempt.endDateTime = Date()
        var resultIntent = Intent(this, ResultActivity::class.java)
        resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        resultIntent.putExtra("QUIZ_ATTEMPT",quizAttempt as Serializable)
        GlobalScope.launch {
            StatsRequests.putQuizAttempt(quizAttempt,
                sharedPreferences.getString("EMAIL",""),
                sharedPreferences.getString("JWT",""))
        }
        startActivity(resultIntent)
    }
    override fun onStart() {

        setupAdapters()
        super.onStart()
    }
    fun setFullScreenWindow(){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
    fun setupAdapters(){
        mAdapter = QuestionFragmentPagerAdapter(this,
            supportFragmentManager,quiz.questions!!)
        question_view_pager.adapter = mAdapter
        question_view_pager.addOnPageChangeListener(this)
    }

    override fun confirmButtonClicked() {
        finishQuiz()
        timer1.cancel()
    }

    override fun cancelButtonClicked() {
        //do nothing
    }

    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        question_id_textView.text = "#${quiz.questions!![position].id}"
        question_number_text_view.text = "${position + 1}/${quiz.questions!!.size}"
    }
}
