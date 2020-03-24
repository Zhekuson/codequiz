package com.nuclearfoxes.codequiz.ui.tests

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipGroupCustomAdapter
import kotlinx.android.synthetic.main.activity_custom_test.*
import kotlinx.android.synthetic.main.fragment_tests.*


class TestsFragment: Fragment() {
    private lateinit var testsViewModel: TestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        testsViewModel =
            ViewModelProviders.of(this).get(TestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tests, container, false)
        var ld:ArrayList<String> = ArrayList<String>()

        ld.add("dv")
        ld.add("dvvd")
        //

        return root
    }

    override fun onStart() {
        super.onStart()
        custom_text_button.setOnClickListener{
            var intentNext = Intent(this.activity,CustomTestActivity::class.java)
            startActivity(intentNext)
        }
    }

}

