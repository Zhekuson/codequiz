package com.nuclearfoxes.codequiz.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nuclearfoxes.codequiz.MainActivity
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
        skip_results_button.setOnClickListener {
            var resultIntent = Intent(this,MainActivity::class.java)
                resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(resultIntent)
        }
        super.onStart()
    }
}
