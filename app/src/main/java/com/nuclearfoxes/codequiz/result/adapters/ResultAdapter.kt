package com.nuclearfoxes.codequiz.result.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.objects.LayoutSetup
import com.nuclearfoxes.data.models.question.Question
import com.nuclearfoxes.data.models.question.QuestionType
import com.nuclearfoxes.data.models.quiz.QuizAttempt


class ResultAdapter(var quizAttempt:QuizAttempt,
                    var mContext:Context)
    :BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertedView = convertView

        val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        convertedView = when(quizAttempt.quiz!!.questions!![position].type){
            QuestionType.MULTIPLE_CHOICE.value-> {
                var layout = inflater.inflate(R.layout.multiple_choice_question_layout, null)
                LayoutSetup.setupMultipleLayout(quizAttempt.quiz!!.questions!![position],
                    mContext,layout,quizAttempt.userQuizAnswer!!)
                return layout
            }
            QuestionType.OPEN.value ->  {
                var layout = inflater.inflate(R.layout.open_question_layout_result, null)
                LayoutSetup.setupOpenLayout(quizAttempt.quiz!!.questions!![position],mContext,
                    layout, quizAttempt.userQuizAnswer!!)

                return layout
            }
            QuestionType.SINGLE_CHOICE.value ->   {
                var layout = inflater.inflate(R.layout.single_choice_question_layout, null)
                LayoutSetup.setupSingleLayout(quizAttempt.quiz!!.questions!![position], mContext,
                    layout,quizAttempt.userQuizAnswer!!)
                return layout
            }
            else ->{
                var layout = inflater.inflate(R.layout.open_question_layout, null)
                LayoutSetup.setupOpenLayout(quizAttempt.quiz!!.questions!![position],
                    mContext,layout, quizAttempt.userQuizAnswer!!)
                return layout
            }
        }
        return convertedView!!
    }

    override fun getItem(position: Int): Any {
        return quizAttempt.quiz!!.questions!![position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return quizAttempt.quiz!!.questions!!.size
    }


}