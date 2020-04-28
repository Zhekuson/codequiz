package com.nuclearfoxes.codequiz.quiz.objects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.android.material.radiobutton.MaterialRadioButton
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.question.Question

object LayoutSetup {
    fun setupOpenLayout(question: Question, context: Context, layout: View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
    }

    fun setupSingleLayout(question: Question, context:Context, layout: View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var radioGroup = layout.findViewById(R.id.single_choice_radio_group) as RadioGroup
        for (answer in question.answers!!) {
            var rb =  MaterialRadioButton(context!!)
            rb.text = answer.answerText
            radioGroup.addView(rb)
        }
    }
    fun setupSingleLayout(question: Question, context:Context, layout: View, userAnswer:String){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var radioGroup = layout.findViewById(R.id.single_choice_radio_group) as RadioGroup
        //radioGroup.setBackgroundColor(context.getColor(R.color.colorAccent))
        for (answer in question.answers!!) {
            var rb =  MaterialRadioButton(context!!)
            rb.isClickable = false
           // if(answer == userAnswer){
                rb.setBackgroundColor(context.getColor(R.color.colorMistakeMain))
                rb.isChecked = true
            radioGroup.addView(rb)
            }
            //if(question.rightAnswer[0] == answer){
              //  rb.setBackgroundColor(context.getColor(R.color.colorCorrectLight))
            //}
            //rb.text = answer

        }


    fun setupMultipleLayout(question: Question, context:Context, layout: View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var answersLayout = layout.findViewById(R.id.checkboxes_layout) as LinearLayout
        for (answer in question.answers!!) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var cb = inflater.inflate(R.layout.single_checkbox_layout, null) as CheckBox
            cb.text = answer.answerText
            answersLayout.addView(cb)
        }

    }
    fun setupMultipleLayout(question: Question, context:Context,
                            layout: View, userAnswers:ArrayList<String>){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var answersLayout = layout.findViewById(R.id.checkboxes_layout) as LinearLayout
        for (answer in question.answers!!) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var cb = inflater.inflate(R.layout.single_checkbox_layout, null) as CheckBox
            cb.text = answer.answerText

           // if(question.rightAnswer.contains(answer)){
          //      cb.setBackgroundColor(context.getColor(R.color.colorMistakeMain))
          //  }
           // if(userAnswers.contains(answer)){
                cb.setBackgroundColor(context.getColor(R.color.colorCorrectLight))
                cb.isChecked = true
           // }
            cb.isClickable = false
            answersLayout.addView(cb)
        }

    }
}