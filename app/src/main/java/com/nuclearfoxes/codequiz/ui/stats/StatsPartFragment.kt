package com.nuclearfoxes.codequiz.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.stats.adapters.StatsRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_stats_part.*

/**
 * position: 0 - today, 1 - this week, 2 - all time
 */
class StatsPartFragment(var position:Int): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var layout = inflater.inflate(
            R.layout.fragment_stats_part,
            container, false)
        when(position){
            0->{
                //TODO today stats
                //stats_list_view.adapter = StatsRecyclerViewAdapter()
                return layout
            }
            1->{
                //TODO week sttats
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