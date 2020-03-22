package com.nuclearfoxes.codequiz.quiz

import android.content.ContextWrapper
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fasterxml.jackson.databind.cfg.ContextAttributes
import com.google.android.material.radiobutton.MaterialRadioButton
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType
import kotlinx.android.synthetic.main.activity_quiz.*
import kotlinx.android.synthetic.main.activity_quiz.view.*
import kotlinx.android.synthetic.main.question_layout.*

class QuestionFragment(val question: Question):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = when(question.type){
             QuestionType.MULTIPLE_CHOICE -> {

                 var layout =inflater.inflate(R.layout.multiple_choice_question_layout,
                     container, false)
                 setupMultipleLayout(layout)
                 return layout
             }
             QuestionType.OPEN -> inflater.inflate(R.layout.open_question_layout,
                 container, false)
             QuestionType.SINGLE_CHOICE -> {

                 var layout = inflater.inflate(R.layout.single_choice_question_layout,
                     container, false)
                 setupSingleLayout(layout)
                 return layout
             }
        }
        return root
    }
    fun setupSingleLayout(layout: View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.question
        var radioGroup = layout.findViewById(R.id.single_choice_radio_group) as RadioGroup
        for (answer in question.answers) {
            var rb =  MaterialRadioButton(context!!)
            rb.text = answer
            radioGroup.addView(rb)
            //TODO add theme wrapper
        }
    }

    fun setupMultipleLayout(layout:View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.question
        //topPanel.question_id_textView.text = question.id
        var answersLayout = layout.findViewById(R.id.checkboxes_layout) as LinearLayout
        for (answer in question.answers) {
            var cb = CheckBox(context)
            cb.text = answer
            answersLayout.addView(cb)
        }
        //TODO add theme wrapper
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