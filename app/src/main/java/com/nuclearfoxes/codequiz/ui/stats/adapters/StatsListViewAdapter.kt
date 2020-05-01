package com.nuclearfoxes.codequiz.ui.stats.adapters

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.quiz.QuizAttempt
import com.nuclearfoxes.data.models.quiz.QuizType

class StatsListViewAdapter(var listQuizAttempts:List<QuizAttempt>,
                           var mContext: Context):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertedView = convertView

        val inflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        when(position){
            0->{
                var attempts = listQuizAttempts.filter {
                    x->x.quiz!!.quizType == QuizType.ALL_RANDOM.value
                }
                convertedView = inflater.inflate(R.layout.bar_chart_layout, null)
                var lineChart = convertedView.findViewById<LineChart>(R.id.data_bar_chart)
                var winrate = ArrayList<Float>()
                for (attempt in attempts){
                    winrate.add(attempt.countRightAnswers().toFloat()/
                            attempt.quiz!!.questions!!.size.toFloat() * 100)
                }
                lineChart.description.isEnabled = false
                lineChart.legend.textSize = 18f
                lineChart.data = LineChartConverter.toLineChartData(winrate,"All random quiz rate, %")
            }
            1->{
                var attempts = listQuizAttempts.filter {
                        x->x.quiz!!.quizType == QuizType.EXAM.value
                }
                convertedView = inflater.inflate(R.layout.bar_chart_layout, null)
                var lineChart = convertedView.findViewById<LineChart>(R.id.data_bar_chart)
                var winrate = ArrayList<Float>()
                for (attempt in attempts){
                    winrate.add(attempt.countRightAnswers().toFloat()/
                            attempt.quiz!!.questions!!.size.toFloat() * 100)
                }
                lineChart.description.isEnabled = false
                lineChart.legend.textSize = 18f
                lineChart.data = LineChartConverter.toLineChartData(winrate,"Exam quiz rate, %")
            }
            2->{
                var attempts = listQuizAttempts.filter {
                        x->x.quiz!!.quizType == QuizType.CUSTOM.value
                }
                convertedView = inflater.inflate(R.layout.bar_chart_layout, null)
                var lineChart = convertedView.findViewById<LineChart>(R.id.data_bar_chart)
                var winrate = ArrayList<Float>()
                for (attempt in attempts){
                    winrate.add(attempt.countRightAnswers().toFloat()/
                            attempt.quiz!!.questions!!.size.toFloat() * 100)
                }
                lineChart.description.isEnabled = false
                lineChart.legend.textSize = 18f
                lineChart.data = LineChartConverter.toLineChartData(winrate,"Custom quiz rate, %")
            }
            else->{
                convertedView = inflater.inflate(R.layout.tag_rate_layout, null)
            }
        }
        return convertedView!!
    }

    override fun getItem(position: Int): Any {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 4
    }
}