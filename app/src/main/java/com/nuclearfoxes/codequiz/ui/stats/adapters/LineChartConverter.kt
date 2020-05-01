package com.nuclearfoxes.codequiz.ui.stats.adapters

import android.graphics.Color
import com.github.mikephil.charting.data.*

object LineChartConverter {
    fun toLineChartData(data:ArrayList<Float>, label:String): LineData {
        var entryList = ArrayList<Entry>(data.size)
        for (i in 0 until data.size) {
            entryList.add(BarEntry(i.toFloat(), data[i]))
        }
        var dataset = LineDataSet(entryList.toList(),label)
        dataset.lineWidth = 3f
        dataset.circleRadius = 3f
        dataset.circleHoleColor = Color.CYAN
        dataset.valueTextSize = 12f
        var linedata = LineData(dataset)
        return linedata
    }
}