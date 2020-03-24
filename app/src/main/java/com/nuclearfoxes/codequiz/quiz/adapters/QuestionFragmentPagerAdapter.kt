package com.nuclearfoxes.codequiz.quiz.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nuclearfoxes.codequiz.quiz.QuestionFragment
import com.nuclearfoxes.data.models.Question

class QuestionFragmentPagerAdapter(val myContext: Context?,
                                   fm: FragmentManager,
                                   var questions:ArrayList<Question>)
    :FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    var fragments:ArrayList<Fragment>
    init {
        fragments = ArrayList()
        for (i in 0 until questions.size){
            fragments.add(QuestionFragment(questions[i]))
        }
    }
    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
    override fun getCount(): Int {
        return questions.size
    }
}