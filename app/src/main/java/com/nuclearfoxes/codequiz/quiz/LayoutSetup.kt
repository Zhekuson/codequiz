package com.nuclearfoxes.codequiz.quiz

import android.content.Context
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.radiobutton.MaterialRadioButton
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.Question

object LayoutSetup {
    fun setupSingleLayout(question:Question,context:Context,layout: View){
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

    fun setupMultipleLayout(question:Question,context:Context,layout: View){
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
}