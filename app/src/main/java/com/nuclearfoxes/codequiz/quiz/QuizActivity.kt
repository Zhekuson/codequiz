package com.nuclearfoxes.codequiz.quiz

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.adapters.QuestionFragmentPagerAdapter
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
                //TODO add start activity
                var resultIntent = Intent(this@QuizActivity,ResultActivity::class.java)
                var rightAnswers:Int = 0
                var questionsAndAnswers = ArrayList<Pair<Question,ArrayList<String>>>()
                for(childNum in 0 until questions.size){
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
                            val str = fragment.savedInfo.getString("ANSWER")
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
                startActivity(resultIntent)
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
        questions1.add(
            Question(
                "1",
                "Which of these types are primitives in C#?",
                QuestionType.MULTIPLE_CHOICE,
                arrayListOf("Primitives", "Basics"),
                arrayListOf("int", "double", "byte", "short", "long"),
                arrayListOf("int", "double", "byte", "short", "long", "List")
            )
        )
        questions1.add(
            Question(
                "2",
                "What will be output after running this code:\n " +
                        "static void SetXY(ref int x, ref int y){\n" +
                        "   x = ++x * 2 - 1;\n" +
                        "   y = y-- * 2;\n" +
                        "static void Main(string[] args){\n" +
                        "   int x = 3;\n" +
                        "   int y = 7;\n" +
                        "   SetXY(ref x, ref y);\n" +
                        "   Console.Write(x+\"+\"+y);\n" +
                        "} \n" +
                        "print *** if compile error will happen," +
                        " +++ if exception will be thrown, --- if no output ",
                QuestionType.OPEN,
                arrayListOf("efe", "fe"),
                arrayListOf("efe", "fe"),
                arrayListOf("efe", "fe")
            )
        )
        questions1.add(
            Question(
                "3",
                "In the namespace (not in the class bounds) can be declared:",
                QuestionType.SINGLE_CHOICE,
                arrayListOf("", "fe"),
                arrayListOf("Class"),
                arrayListOf("Static method", "Class", "Field", "Non-static method", "Static field")
            )
        )
        questions1.add(
            Question(
                "3",
                "In the namespace (not in the class bounds) can be declared:",
                QuestionType.SINGLE_CHOICE,
                arrayListOf("", "fe"),
                arrayListOf("Class"),
                arrayListOf("Static method", "Class", "Field", "Non-static method", "Static field")
            )
        )
        questions1.add(
            Question(
                "3",
                "In the namespace (not in the class bounds) can be declared:",
                QuestionType.SINGLE_CHOICE,
                arrayListOf("", "fe"),
                arrayListOf("Class"),
                arrayListOf("Static method", "Class", "Field", "Non-static method", "Static field")
            )
        )
        questions1.add(
            Question(
                "3",
                "In the namespace (not in the class bounds) can be declared:",
                QuestionType.SINGLE_CHOICE,
                arrayListOf("", "fe"),
                arrayListOf("Class"),
                arrayListOf("Static method", "Class", "Field", "Non-static method", "Static field")
            )
        )
        questions1.add(
            Question(
                "3",
                "In the namespace (not in the class bounds) can be declared:",
                QuestionType.SINGLE_CHOICE,
                arrayListOf("", "fe"),
                arrayListOf("Class"),
                arrayListOf("Static method", "Class", "Field", "Non-static method", "Static field")
            )
        )

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
