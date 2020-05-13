package com.nuclearfoxes.codequiz.ui.tests

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.ui.tests.custom.CustomTestActivity
import com.nuclearfoxes.codequiz.ui.tests.loading.QuizLoadingActivity
import com.nuclearfoxes.data.exceptions.QuizNotFoundException
import com.nuclearfoxes.data.models.tags.Tag
import kotlinx.android.synthetic.main.fragment_tests.*
import java.lang.Exception
import java.lang.NumberFormatException


class TestsFragment: Fragment() {
    private lateinit var testsViewModel: TestsViewModel
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        testsViewModel =
            ViewModelProviders.of(this).get(TestsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_tests, container, false)
        setupClickListeners(root)
        sharedPreferences = activity!!.getSharedPreferences("MAIN", Context.MODE_PRIVATE)
        return root
    }
    fun setupClickListeners(root:View){
        root.findViewById<MaterialButton>(R.id.custom_test_button).setOnClickListener{
            var intentNext = Intent(this.activity,
                CustomTestActivity::class.java)
            startActivity(intentNext)
        }
        root.findViewById<MaterialButton>(R.id.all_random_button).setOnClickListener{
            var intentNext = Intent(context,QuizLoadingActivity::class.java)
            intentNext.putExtra("MODE", "RANDOM")
            startActivity(intentNext)
        }
        root.findViewById<MaterialButton>(R.id.exam_mode_button).setOnClickListener{
            var intentNext = Intent(context,QuizLoadingActivity::class.java)
            intentNext.putExtra("MODE", "EXAM")
            startActivity(intentNext)
        }
        root.findViewById<MaterialButton>(R.id.quiz_by_id_button).setOnClickListener {
            try {
                var intentNext = Intent(context, QuizLoadingActivity::class.java)
                intentNext.putExtra("ID", quiz_id_edit_text.text.toString().toInt())
                intentNext.putExtra("MODE", "BY_ID")
                startActivity(intentNext)
            }catch (e:NumberFormatException){
                Toast.makeText(context,"Input valid ID!",Toast.LENGTH_LONG).show()
            }catch (e:Exception) {
                Toast.makeText(context,"Error happened",Toast.LENGTH_LONG).show()
            }catch (e:QuizNotFoundException){
                Toast.makeText(context,"Quiz not found",Toast.LENGTH_LONG).show()
            }
        }
    }


}

