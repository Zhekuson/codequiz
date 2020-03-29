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
import com.google.android.material.button.MaterialButton
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
        setupClickListeners(root)
        return root
    }
    fun setupClickListeners(root:View){
        root.findViewById<MaterialButton>(R.id.custom_test_button).setOnClickListener{
            var intentNext = Intent(this.activity,CustomTestActivity::class.java)
            startActivity(intentNext)
        }
        root.findViewById<MaterialButton>(R.id.all_random_button).setOnClickListener{
            var intentNext = Intent(context,QuizActivity::class.java)
            intentNext.putExtra("TIME_MS", 600000L)
            startActivity(intentNext)
        }
        root.findViewById<MaterialButton>(R.id.exam_mode_button).setOnClickListener{
            var intentNext = Intent(context,QuizActivity::class.java)
            intentNext.putExtra("TIME_MS", 3600000L)
            startActivity(intentNext)
        }
    }
    override fun onStart() {
        super.onStart()

    }

}

