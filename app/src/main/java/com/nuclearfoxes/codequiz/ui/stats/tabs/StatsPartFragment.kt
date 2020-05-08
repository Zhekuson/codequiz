package com.nuclearfoxes.codequiz.ui.stats.tabs

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.stats.adapters.StatsListViewAdapter
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import kotlinx.android.synthetic.main.fragment_stats_part.*

/**
 * position: 0 - today, 1 - this week, 2 - all time
 */
class StatsPartFragment(var position:Int,var listQuizAttempt: List<QuizAttempt>): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = inflater.inflate(
            R.layout.fragment_stats_part,
            container, false)
            var lv:ListView = layout.findViewById<ListView>(R.id.stats_list_view)
            lv.adapter = StatsListViewAdapter(listQuizAttempt,context!!)
            return layout
        }

}