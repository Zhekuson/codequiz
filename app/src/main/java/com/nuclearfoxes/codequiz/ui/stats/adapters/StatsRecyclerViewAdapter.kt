package com.nuclearfoxes.codequiz.ui.stats.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nuclearfoxes.codequiz.R

class StatsRecyclerViewAdapter(var allRandomData:ArrayList<Float>,
                               var examData:ArrayList<Float>,
                               var allTagsStats:ArrayList<Pair<String,Float>>, var mContext: Context
                               ):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertedView = convertView

        val inflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        //TODO add data converter and bind
        when(position){
            0->{
                convertedView = inflater.inflate(R.layout.bar_chart_layout, null)
            }
            1->{
                convertedView = inflater.inflate(R.layout.bar_chart_layout, null)
            }
            else->{
                convertedView = inflater.inflate(R.layout.tag_rate_layout, null)
            }
        }
        return convertedView!!
    }

    override fun getItem(position: Int): Any {
        return when(position){
            0->{
                allRandomData
            }
            1->{
                examData
            }
            else->{
                allTagsStats
            }
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return 3
    }
}