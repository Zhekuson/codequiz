package com.nuclearfoxes.codequiz.quiz

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.objects.LayoutSetup
import com.nuclearfoxes.codequiz.quiz.objects.LayoutSetup.setupOpenLayout
import com.nuclearfoxes.codequiz.quiz.objects.LayoutSetup.setupSingleLayout
import com.nuclearfoxes.data.models.question.Question
import com.nuclearfoxes.data.models.question.QuestionType
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
             QuestionType.OPEN ->{
               var layout = inflater.inflate(R.layout.open_question_layout,
                   container, false)
                 setupOpenLayout(question,context!!,layout)
                 return layout
             }
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
        when(question.type){
            QuestionType.SINGLE_CHOICE->{
                single_choice_radio_group.setOnCheckedChangeListener{
                        radioGroup: RadioGroup, i: Int ->
                    storeData()
                }
            }
            QuestionType.OPEN->{
                open_question_edit_text.addTextChangedListener(object :TextWatcher{
                    override fun afterTextChanged(s: Editable?) {
                        storeData()
                    }

                    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                })
            }
            QuestionType.MULTIPLE_CHOICE->{
                for(child in checkboxes_layout.children){
                    child.setOnClickListener {
                        storeData()
                        //it.visibility = View.INVISIBLE
                    }
                }

            }
        }
    }
    fun storeData(){
        when(question.type){
            QuestionType.SINGLE_CHOICE->{
                for (i in 0 until single_choice_radio_group.childCount){
                    val rb= (single_choice_radio_group.getChildAt(i) as RadioButton)
                    if(rb.isChecked){
                        savedInfo.putString("ANSWER",rb.text.toString())
                        break
                    }
                }


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
                for (i in 0 until single_choice_radio_group.childCount){
                    val rb= (single_choice_radio_group.getChildAt(i) as RadioButton)
                    if(rb.text == savedInfo.getString("ANSWER")){
                        rb.isChecked = true
                        break
                    }
                }
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
        bindListeners()
        alreadyStarted = true
        super.onStart()
    }

}