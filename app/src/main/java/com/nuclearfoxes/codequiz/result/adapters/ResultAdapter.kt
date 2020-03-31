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


class ResultAdapter(var questionsAndAnswers:ArrayList<Pair<Question,ArrayList<String>>>,
                    var mContext:Context)
    :BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertedView = convertView

        val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        convertedView = when(questionsAndAnswers[position].first.type){
            QuestionType.MULTIPLE_CHOICE-> {
                var layout = inflater.inflate(R.layout.multiple_choice_question_layout, null)
                LayoutSetup.setupMultipleLayout(questionsAndAnswers[position].first,
                    mContext,layout,questionsAndAnswers[position].second)
                return layout
            }
            QuestionType.OPEN ->  {
                var layout = inflater.inflate(R.layout.open_question_layout, null)
                LayoutSetup.setupOpenLayout(questionsAndAnswers[position].first,mContext,layout)
                return layout
            }
            QuestionType.SINGLE_CHOICE ->   {
                var layout = inflater.inflate(R.layout.single_choice_question_layout, null)
                LayoutSetup.setupSingleLayout(questionsAndAnswers[position].first, mContext,
                    layout,questionsAndAnswers[position].second[0])
                return layout
            }
        }
        return convertedView!!
    }

    override fun getItem(position: Int): Any {
        return questionsAndAnswers[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getCount(): Int {
        return questionsAndAnswers.size
    }


}