package com.nuclearfoxes.codequiz.ui.stats.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.stats.StatsViewModel
import com.nuclearfoxes.codequiz.ui.stats.adapters.StatsPartFragmentAdapter
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StatsFragment:Fragment(), StatsViewModel.LoadingFinishedListener {
    private lateinit var statsViewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statsViewModel =
            ViewModelProviders.of(this).get(StatsViewModel::class.java)
        GlobalScope.launch {
            statsViewModel.getQuizAttempts(this@StatsFragment.context!!, this@StatsFragment)
        }
        val root = inflater.inflate(R.layout.fragment_stats, container, false)

        return root
    }


    override fun onFinish() {
        GlobalScope.launch(Dispatchers.Main) {
            stats_view_pager.adapter = StatsPartFragmentAdapter(
                statsViewModel.listQuizAttempt, childFragmentManager!!
            )
            stats_tab_layout.setupWithViewPager(stats_view_pager)
        }
    }
}