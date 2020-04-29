package com.nuclearfoxes.codequiz.quiz.objects

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.google.android.material.radiobutton.MaterialRadioButton
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.answers.UserQuizAnswer
import com.nuclearfoxes.data.models.question.Question

object LayoutSetup {
    fun setupOpenLayout(question: Question, context: Context, layout: View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
    }
    fun setupOpenLayout(question: Question, context: Context, layout: View, userQuizAnswer: UserQuizAnswer){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var answerTextView =  layout.findViewById<TextView>(R.id.open_question_answer_text_view)
        if(question.answers != null) {
            answerTextView.text = question.answers!![0].answerText
            answerTextView.setBackgroundColor(context.getColor(R.color.colorMistakeMain))
        }
        if(userQuizAnswer.userAnswers != null) {
            for (answer in userQuizAnswer.userAnswers!!) {
                if (answer.questionId == question.id){
                    answerTextView.setBackgroundColor(context.getColor(R.color.colorCorrectLight))
                }
            }
        }

    }

    fun setupSingleLayout(question: Question, context:Context, layout: View){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var radioGroup = layout.findViewById(R.id.single_choice_radio_group) as RadioGroup
        if(question.answers!= null) {
            for (answer in question.answers!!) {
                var rb = MaterialRadioButton(context!!)
                rb.text = answer.answerText
                radioGroup.addView(rb)
            }
        }
    }
    fun setupSingleLayout(question: Question, context:Context, layout: View, userQuizAnswer: UserQuizAnswer){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var radioGroup = layout.findViewById(R.id.single_choice_radio_group) as RadioGroup
        for (answer in question.answers!!) {
            var rb =  MaterialRadioButton(context!!)
            rb.text = answer.answerText
            rb.isClickable = false
            if(answer.isRight!!){
                rb.setBackgroundColor(context.getColor(R.color.colorMistakeMain))
            }
            if(userQuizAnswer.userAnswers != null){
            if(userQuizAnswer.userAnswers!!.contains(answer)){
                rb.isChecked = true
                if(answer.isRight!!){
                    rb.setBackgroundColor(context.getColor(R.color.colorCorrectLight))
                }else{
                    rb.setBackgroundColor(context.getColor(R.color.colorMistakeMain))
                }
            }
            }
            radioGroup.addView(rb)
        }
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
                            layout: View, userQuizAnswer: UserQuizAnswer){
        var questionTextView= layout.findViewById(R.id.question_text_textView) as TextView
        questionTextView.text = question.questionText
        var answersLayout = layout.findViewById(R.id.checkboxes_layout) as LinearLayout
        var userAnswerIsRight = true
        for (answer in question.answers!!) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            var cb = inflater.inflate(R.layout.single_checkbox_layout, null) as CheckBox
            cb.text = answer.answerText

            if(answer.isRight!!){
                cb.setBackgroundColor(context.getColor(R.color.colorMistakeMain))

            }
            if(userQuizAnswer.userAnswers != null){
                if(userQuizAnswer.userAnswers!!.contains(answer)){
                    cb.isChecked = true
                    if(answer.isRight!!){
                        cb.setBackgroundColor(context.getColor(R.color.colorCorrectLight))

                    }else{
                        cb.setBackgroundColor(context.getColor(R.color.colorMistakeMain))

                    }
                }
            }

            cb.isClickable = false
            answersLayout.addView(cb)
        }
    }
}