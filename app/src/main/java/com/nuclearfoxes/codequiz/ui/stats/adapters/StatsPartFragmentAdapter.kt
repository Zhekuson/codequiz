package com.nuclearfoxes.codequiz.ui.stats.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.gms.common.internal.Constants
import com.nuclearfoxes.codequiz.ui.stats.tabs.StatsPartFragment
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import java.time.Instant
import java.time.Period
import java.util.*
import kotlin.collections.ArrayList

class StatsPartFragmentAdapter(var listQuizAttempt: ArrayList<QuizAttempt>,fm:FragmentManager)
    :FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        var filteredQuizAttempts:List<QuizAttempt>
        when(position){
            0-> {
                filteredQuizAttempts = listQuizAttempt.filter { x->
                    x.startDateTime!!.day == Date().day &&
                    x.startDateTime!!.month == Date().month &&
                    x.startDateTime!!.year == Date().year
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
}