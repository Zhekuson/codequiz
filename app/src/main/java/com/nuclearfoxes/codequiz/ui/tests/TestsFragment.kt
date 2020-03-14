package com.nuclearfoxes.codequiz.ui.tests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nuclearfoxes.codequiz.R


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
        return root
    }
}
/*     val textView: TextView = root.findViewById(R.id.text_share)
      testsViewModel.text.observe(this, Observer {
         textView.text = it
     })*/