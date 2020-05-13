package com.nuclearfoxes.codequiz.ui.stats.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.*
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.stats.StatsViewModel
import com.nuclearfoxes.codequiz.ui.stats.adapters.StatsPartFragmentAdapter
import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.QuizAttemptsNotFound
import com.nuclearfoxes.data.models.quiz.Quiz
import kotlinx.android.synthetic.main.fragment_stats.*
import kotlinx.coroutines.*
import java.net.UnknownHostException


class StatsFragment:Fragment(), StatsViewModel.LoadingFinishedListener {
    private lateinit var statsViewModel: StatsViewModel
    private   var coroutineExceptionHandler = CoroutineExceptionHandler{_ , t->
        MainScope().launch {
            when (t) {
                is UnknownHostException -> {
                    Toast.makeText(this@StatsFragment.context,
                        "Error: no internet connection", Toast.LENGTH_LONG).show()
                }
                is InternalServerErrorException -> {
                    Toast.makeText(this@StatsFragment.context,
                        "Internal server error", Toast.LENGTH_LONG).show()
                }
                is QuizAttemptsNotFound -> {
                    Toast.makeText(this@StatsFragment.context,
                        "No data for stats found", Toast.LENGTH_LONG).show()
                }
            }
            stats_progress_bar.visibility = View.GONE
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        statsViewModel =
            ViewModelProviders.of(this).get(StatsViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_stats, container, false)
        root.findViewById<ProgressBar>(R.id.stats_progress_bar).visibility = View.VISIBLE
        GlobalScope.launch(coroutineExceptionHandler) {
            statsViewModel.getQuizAttempts(this@StatsFragment.context!!, this@StatsFragment)
            MainScope().launch {
                stats_progress_bar.visibility = View.GONE
            }
        }
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