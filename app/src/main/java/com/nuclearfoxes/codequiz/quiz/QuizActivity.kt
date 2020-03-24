package com.nuclearfoxes.codequiz.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.adapters.QuestionFragmentPagerAdapter
import com.nuclearfoxes.codequiz.result.ResultActivity
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType

import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.multiple_choice_question_layout.*
import kotlinx.android.synthetic.main.open_question_layout.*
import kotlinx.android.synthetic.main.single_choice_question_layout.*
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
                //TODO add start activity
                var resultIntent = Intent(this@QuizActivity,ResultActivity::class.java)
                var rightAnswers:Int = 0
                var questionsAndAnswers = ArrayList<Pair<Question,ArrayList<String>>>()
                for(childNum in 0 until question_view_pager.children.count()){
                    var answ = ArrayList<String>()
                    var fragment = mAdapter.getItem(childNum) as QuestionFragment// question_view_pager.adapter
                    when(questions[childNum].type){
                        QuestionType.MULTIPLE_CHOICE->{
                            val answers = fragment.savedInfo.getBooleanArray("ANSWER")
                            var res = ArrayList<String>()
                            for (i in 0 until answers.size){
                                if(answers[i]){
                                    res.add(i.toString())
                                }
                            }
                            answ.addAll(res)
                        }
                        QuestionType.OPEN->{
                            val answer = fragment.savedInfo.getString("ANSWER")
                            answ.add(answer)
                        }
                        QuestionType.SINGLE_CHOICE->{
                            val str = (findViewById<RadioButton>(fragment.savedInfo.getInt("ANSWER"))).text
                            answ.add(str.toString())
                        }
                    }
                    questionsAndAnswers.add(Pair(questions[childNum],answ))
                    //TODO ADD EQUALITY
                    if (answ == questions[childNum].rightAnswer){
                        rightAnswers++
                    }
                }

                resultIntent.putExtra("QUESTIONS_AND_ANSWERS",questionsAndAnswers)
                resultIntent.putExtra("RIGHT_ANSWERS_COUNT",rightAnswers)
            }
        }
        timer.start()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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
        var questions1: ArrayList<Question> = ArrayList()
        questions1.add(Question("d",
            "ef",
            QuestionType.MULTIPLE_CHOICE,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))
        questions1.add(Question("vd",
            "ef",
            QuestionType.SINGLE_CHOICE,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))
        questions1.add(Question("vd",
            "ef",
            QuestionType.OPEN,
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe"),
            arrayListOf("efe","fe")))

        mAdapter =             QuestionFragmentPagerAdapter(
            this,
            supportFragmentManager,
            questions1
        )
        questions = questions1
        question_view_pager.adapter = mAdapter

        /*question_view_pager.adapter = QuestionFragmentPagerAdapter(this,
                supportFragmentManager,
                questions)*/

    }
}
