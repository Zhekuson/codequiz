package com.nuclearfoxes.codequiz.ui.tests

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipGroupCustomAdapter
import com.nuclearfoxes.codequiz.ui.tests.adapters.CloseIconClickListener
import kotlinx.android.synthetic.main.activity_custom_test.*

class CustomTestActivity : AppCompatActivity(), CloseIconClickListener, ChooseTagFragment.ConfirmationListener {
    val MAX_TIME:Int = 60
    val MIN_TIME:Int = 1
    val STEP_TIME = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_test)
        this.getSupportActionBar()?.hide()
        setupAdaptersAndClickListeners()
    }

    override fun onStart() {

        super.onStart()
    }
    fun setupAdaptersAndClickListeners(){
        go_button.setOnClickListener{
            val intentNext = Intent(this, QuizActivity::class.java)
            intentNext.putExtra("TIME_MS", (time_seek_bar_tests.progress+1)*STEP_TIME*60*1000L)
            intentNext.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intentNext)
        }
        var ld = ArrayList<String>()
        ld.add("Callbacks")
        ld.add("Events")
        ChipGroupCustomAdapter(this!!,findViewById(R.id.chip_group_test_tags),ld,this)
        time_seek_bar_tests.max = (MAX_TIME - MIN_TIME)/STEP_TIME
        time_seek_bar_tests.setOnSeekBarChangeListener(object :SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                time_textView.text = (MIN_TIME + progress*STEP_TIME).toString() + " min"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        add_tag_chip.setOnClickListener {
            ChooseTagFragment().show(supportFragmentManager,"TAG")
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
