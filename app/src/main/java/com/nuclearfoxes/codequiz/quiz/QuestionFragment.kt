package com.nuclearfoxes.codequiz.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType

class QuestionFragment(val question: Question):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = when(question.type){
             QuestionType.MULTIPLE_CHOICE -> inflater.inflate(R.layout.multiple_choice_question_layout, container, false)
             QuestionType.OPEN -> inflater.inflate(R.layout.open_question_layout, container, false)
             QuestionType.SINGLE_CHOICE -> inflater.inflate(R.layout.single_choice_question_layout, container, false)
        }
        return root
    }

    override fun onSaveInstanceState(outState: Bundle) {
        //TODO save instance
        //outState.putSerializable("ANSWER", "")
        super.onSaveInstanceState(outState)

    }
    override fun onStart() {

        super.onStart()
    }
    fun bindQuestion(){

    }
}