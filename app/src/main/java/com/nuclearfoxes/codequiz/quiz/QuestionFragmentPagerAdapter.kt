package com.nuclearfoxes.codequiz.quiz

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nuclearfoxes.data.models.Question

class QuestionFragmentPagerAdapter(val myContext: Context?,
                                   fm: FragmentManager,
                                   var questions:ArrayList<Question>)
    :FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return QuestionFragment(questions[position])
    }

    override fun getCount(): Int {
        return questions.size
    }
}