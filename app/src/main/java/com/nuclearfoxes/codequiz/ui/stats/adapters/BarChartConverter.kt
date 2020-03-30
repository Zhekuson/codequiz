package com.nuclearfoxes.codequiz.ui.stats.adapters

import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.nuclearfoxes.codequiz.R

object BarChartConverter {
    fun toBarChartData(data:ArrayList<Float>, label:String):BarData{
        var entryList:ArrayList<BarEntry> = ArrayList()
        for (i in 0 until data.size) {
            entryList.add(BarEntry(i.toFloat(), data[i]))
        }
        var dataset = BarDataSet(entryList,label)
        var bardata = BarData(dataset)
        return bardata
    }
}