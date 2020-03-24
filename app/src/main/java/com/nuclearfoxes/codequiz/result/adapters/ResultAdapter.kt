package com.nuclearfoxes.codequiz.result.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.LayoutSetup
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType


class ResultAdapter(var questionsAndAnswers:ArrayList<Pair<Question,ArrayList<String>>>,
                    var mContext:Context)
    :BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertedView = convertView

        val inflater =
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //TODO ADD BACKGROUND
        convertedView = when(questionsAndAnswers[position].first.type){
            QuestionType.MULTIPLE_CHOICE-> {
                var layout = inflater.inflate(R.layout.multiple_choice_question_layout, null)
                LayoutSetup.setupMultipleLayout(questionsAndAnswers[position].first, mContext,layout)
                return layout
            }
            QuestionType.OPEN ->  {
                inflater.inflate(R.layout.open_question_layout, null)
            }
            QuestionType.SINGLE_CHOICE ->   {
                var layout = inflater.inflate(R.layout.single_choice_question_layout, null)
                LayoutSetup.setupSingleLayout(questionsAndAnswers[position].first, mContext,layout)
                return layout
            }
        }
        return convertedView
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