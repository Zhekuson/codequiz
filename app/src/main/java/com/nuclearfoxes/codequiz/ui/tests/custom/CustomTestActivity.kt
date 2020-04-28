package com.nuclearfoxes.codequiz.ui.tests.custom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipGroupCustomAdapter
import com.nuclearfoxes.codequiz.ui.tests.adapters.CloseIconClickListener
import com.nuclearfoxes.codequiz.ui.tests.loading.QuizLoadingActivity
import com.nuclearfoxes.data.models.tags.Tag
import kotlinx.android.synthetic.main.activity_custom_test.*

class CustomTestActivity : AppCompatActivity(), CloseIconClickListener,
    ChooseTagFragment.ConfirmationListener {
    var MAX_QUESTIONS_COUNT = 40
    var MIN_QUESTIONS_COUNT = 1
    val STEP_QUESTIONS = 1
    val MAX_TIME:Int = 60
    val MIN_TIME:Int = 1
    val STEP_TIME = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_test)
        this.getSupportActionBar()?.hide()
        setupAdaptersAndClickListeners()
    }
    fun setupAdaptersAndClickListeners(){
        go_button.setOnClickListener{
            val intentNext = Intent(this, QuizLoadingActivity::class.java)
            intentNext.putExtra("MODE", "CUSTOM")
            intentNext.putExtra("MINUTES", time_seek_bar_tests.progress+1)
            intentNext.putExtra("QUESTIONS_COUNT", question_count_seek_bar.progress+1)
            startActivity(intentNext)
        }
        var ld = ArrayList<Tag>()
        ld.add(Tag(1,"Callbacks"))
        ld.add(Tag(2,"Events"))
        ChipGroupCustomAdapter(this!!,findViewById(R.id.chip_group_test_tags),ld,this)
        time_seek_bar_tests.max = (MAX_TIME - MIN_TIME)/STEP_TIME
        time_seek_bar_tests.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                time_textView.text = (MIN_TIME + progress*STEP_TIME).toString() + " min"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        question_count_seek_bar.max = (MAX_QUESTIONS_COUNT - MIN_QUESTIONS_COUNT)/STEP_QUESTIONS
        question_count_seek_bar.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                questions_count_textView.text = (MIN_QUESTIONS_COUNT + progress*STEP_QUESTIONS).toString() + " questions"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        var tags = ArrayList<Pair<Tag,Boolean>>()
        tags.add(Pair(ld[0],false))
        tags.add(Pair(ld[1],true))
        add_tag_chip.setOnClickListener {
            ChooseTagFragment(tags).show(supportFragmentManager,"TAG")
        }
    }



    override fun onClose(chip: Chip) {
        (chip.parent as ChipGroup).removeView(chip)
    }

    override fun confirmButtonClicked() {

    }

    override fun cancelButtonClicked() {

    }
}
