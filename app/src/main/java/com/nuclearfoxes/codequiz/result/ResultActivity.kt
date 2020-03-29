package com.nuclearfoxes.codequiz.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.result.adapters.ResultAdapter
import com.nuclearfoxes.data.models.question.Question
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        this.getSupportActionBar()?.hide()
    }

    override fun onStart() {
        var results = intent
            .getSerializableExtra("QUESTIONS_AND_ANSWERS")
                as ArrayList<Pair<Question,ArrayList<String>>>
        result_list_view.adapter = ResultAdapter(results,this)
        super.onStart()
    }
}
