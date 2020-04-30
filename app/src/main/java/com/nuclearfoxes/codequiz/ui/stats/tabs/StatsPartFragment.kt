package com.nuclearfoxes.codequiz.ui.stats.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.stats.adapters.StatsListViewAdapter
import com.nuclearfoxes.codequiz.ui.stats.adapters.StatsRecyclerViewAdapter
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import kotlinx.android.synthetic.main.fragment_stats_part.*

/**
 * position: 0 - today, 1 - this week, 2 - all time
 */
class StatsPartFragment(var position:Int, var listQuizAttempt: List<QuizAttempt>): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = inflater.inflate(
            R.layout.fragment_stats_part,
            container, false)
        listQuizAttempt
        when(position){
            0->{
                //TODO today stats
                stats_list_view.adapter = StatsListViewAdapter(layout, listQuizAttempt)
                return layout
            }
            1->{
                //TODO week stats
                //stats_list_view.adapter = StatsRecyclerViewAdapter()
                return layout
            }
            else->{
                //TODO all time stats
                //stats_list_view.adapter = StatsRecyclerViewAdapter()
                return layout
            }
        }

    }
}