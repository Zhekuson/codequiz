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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StatsFragment:Fragment() {
    private lateinit var statsViewModel: StatsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statsViewModel =
            ViewModelProviders.of(this).get(StatsViewModel::class.java)
        GlobalScope.launch {
            statsViewModel.getQuizAttempts(this@StatsFragment.context!!)
        }
        val root = inflater.inflate(R.layout.fragment_stats, container, false)
        return root
    }

    fun setupCharts(root:View){

        var all_random_chart = root.findViewById<BarChart>(0)
        var entryList:ArrayList<BarEntry> = ArrayList()
        for (i in 0..7){
            entryList.add(BarEntry(i.toFloat(),i%7.toFloat()))
        }
        //var list:MutableList<Entry> = mutableListOf(0,3,4,3)

        var dataset = BarDataSet(entryList,"rvd")
        dataset.color = R.color.colorCorrectLight
        var dataset1 = BarData(dataset)



        all_random_chart.data = dataset1
        all_random_chart.setScaleEnabled(true)
    }
}