package com.nuclearfoxes.codequiz.quiz

import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.LayoutSetup.setupSingleLayout
import com.nuclearfoxes.data.models.Question
import com.nuclearfoxes.data.models.QuestionType
import kotlinx.android.synthetic.main.multiple_choice_question_layout.*
import kotlinx.android.synthetic.main.open_question_layout.*
import kotlinx.android.synthetic.main.single_choice_question_layout.*


class QuestionFragment(val question: Question):Fragment() {
    var savedInfo: Bundle = Bundle()
    var alreadyStarted = false

    override fun onPause() {
        super.onPause()
        storeData()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = when(question.type){
             QuestionType.MULTIPLE_CHOICE -> {

                 var layout =inflater.inflate(
                     R.layout.multiple_choice_question_layout,
                     container, false)
                 LayoutSetup.setupMultipleLayout(question,context!!,layout)
                 return layout
             }
             QuestionType.OPEN -> inflater.inflate(R.layout.open_question_layout,
                 container, false)
             QuestionType.SINGLE_CHOICE -> {

                 var layout = inflater.inflate(R.layout.single_choice_question_layout,
                     container, false)
                 setupSingleLayout(question,context!!,layout)
                 return layout
             }
        }

        return root
    }
    fun bindListeners(){
        
    }
    fun storeData(){
        when(question.type){
            QuestionType.SINGLE_CHOICE->{
                savedInfo.putInt("ANSWER",single_choice_radio_group.checkedRadioButtonId)
            }
            QuestionType.OPEN->{
                savedInfo.putString("ANSWER", open_question_edit_text.text.toString())
            }
            QuestionType.MULTIPLE_CHOICE->{
                var answers = BooleanArray(checkboxes_layout.childCount)
                for (cb in 0 until checkboxes_layout.childCount){
                    answers[cb] = (checkboxes_layout.getChildAt(cb) as CheckBox).isChecked
                }
                savedInfo.putBooleanArray("ANSWER",answers)
            }
        }
    }
    fun restoreData(){
        when (question.type) {
            QuestionType.SINGLE_CHOICE -> {
                single_choice_radio_group.check(savedInfo!!.getInt("ANSWER"))
            }
            QuestionType.OPEN -> {
                var str: Editable = Editable.Factory.getInstance().newEditable(
                    savedInfo!!.getString("ANSWER")
                )
                open_question_edit_text.text = str
            }
            QuestionType.MULTIPLE_CHOICE -> {
                var answers = savedInfo!!.getBooleanArray("ANSWER")
                for (cb in 0 until checkboxes_layout.childCount) {
                    if (answers[cb]) {
                        (checkboxes_layout.getChildAt(cb) as CheckBox).isChecked = true
                    }
                }

            }
        }
    }
    override fun onStart() {
        if(alreadyStarted) {
            restoreData()
        }
        alreadyStarted = true
        super.onStart()
    }

}