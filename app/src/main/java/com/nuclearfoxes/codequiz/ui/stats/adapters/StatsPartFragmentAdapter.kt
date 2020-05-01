package com.nuclearfoxes.codequiz.ui.stats.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nuclearfoxes.codequiz.ui.stats.tabs.StatsPartFragment
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import java.util.*

class StatsPartFragmentAdapter(var listQuizAttempt: ArrayList<QuizAttempt>,fm:FragmentManager)
    :FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        var filteredQuizAttempts:List<QuizAttempt>
        when(position){
            0-> {
                filteredQuizAttempts = listQuizAttempt.filter { x->
                    Date().time - x.startDateTime!!.time < 86400000
                }
            }
            1->{
                filteredQuizAttempts = listQuizAttempt.filter { x->
                    Date().time - x.startDateTime!!.time < 604800000L
                }
            }
            else->{
                filteredQuizAttempts = listQuizAttempt
            }
        }
        return StatsPartFragment(position,filteredQuizAttempts)
    }

    override fun getCount(): Int {
        return 3
    }
    override fun getPageTitle(position: Int): CharSequence? { // генерируем заголовок в зависимости от позиции
        return when(position){
            0->"Today"
            1->"Week"
            else->"All time"
        }
    }
}