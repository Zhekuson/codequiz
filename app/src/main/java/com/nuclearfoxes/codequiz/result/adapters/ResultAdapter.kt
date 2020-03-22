package com.nuclearfoxes.codequiz.result.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.nuclearfoxes.data.models.Question

class ResultAdapter(questionsAndAnswers:ArrayList<Pair<Question,ArrayList<String>>>):BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItem(position: Int): Any {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemId(position: Int): Long {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}