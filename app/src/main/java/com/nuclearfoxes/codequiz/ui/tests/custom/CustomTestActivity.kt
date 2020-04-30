package com.nuclearfoxes.codequiz.ui.tests.custom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipFactory
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipGroupCustomAdapter
import com.nuclearfoxes.codequiz.ui.tests.adapters.CloseIconClickListener
import com.nuclearfoxes.codequiz.ui.tests.loading.QuizLoadingActivity
import com.nuclearfoxes.data.api.TagsRequests
import com.nuclearfoxes.data.models.tags.Tag
import com.nuclearfoxes.data.models.tags.TagCountPair
import kotlinx.android.synthetic.main.activity_custom_test.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CustomTestActivity : AppCompatActivity(), CloseIconClickListener,
    ChooseTagFragment.ConfirmationListener {
    var MAX_QUESTIONS_COUNT = 1
    var MIN_QUESTIONS_COUNT = 1
    val STEP_QUESTIONS = 1
    val MAX_TIME:Int = 60
    val MIN_TIME:Int = 1
    val STEP_TIME = 1
    var tagCountPairs: ArrayList<TagCountPair> = ArrayList()
    lateinit var sharedPreferences: SharedPreferences
    var tagsTags:ArrayList<Pair<TagCountPair,Boolean>> = ArrayList()
    lateinit var adapter:ChipGroupCustomAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_test)
        sharedPreferences = this.getSharedPreferences("MAIN", Context.MODE_PRIVATE)
        GlobalScope.launch {
            tagCountPairs = TagsRequests.getTagCountPairs(sharedPreferences.getString("JWT",""))
        }
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

        adapter = ChipGroupCustomAdapter(this!!,findViewById(R.id.chip_group_test_tags),
            tagCountPairs,this)
        add_tag_chip.setOnClickListener {
            if(!tagCountPairs.isEmpty()) {
                if(tagsTags.isEmpty()){
                    for (tagCountPair in tagCountPairs) {
                       tagsTags.add(Pair(tagCountPair,false))
                    }
                }
            }
            ChooseTagFragment(tagsTags).show(supportFragmentManager,"TAG")
        }
    }



    override fun onClose(chip: Chip) {
        (chip.parent as ChipGroup).removeView(chip)
        for(i in 0 until tagsTags.size) {
            if (tagsTags[i].first.tag.name == chip.text) {
                tagsTags[i] = tagsTags[i].copy(first = tagsTags[i].first, second = false)
                MAX_QUESTIONS_COUNT -= tagsTags[i].first.count
                break
            }
        }
        question_count_seek_bar.max = (MAX_QUESTIONS_COUNT - MIN_QUESTIONS_COUNT)/STEP_QUESTIONS
        TagsRequests.getMaxCountQuestions(
            sharedPreferences.getString("JWT",""),
            )
    }

    override fun confirmButtonClicked() {
        var counter = 0
        adapter.removeAllExceptAdd()
        var tags:ArrayList<Tag> = ArrayList()
        for (tagTag in tagsTags){
            if(tagTag.second){
                tags.add(tagTag.first.tag)
                counter += tagTag.first.count
                adapter.addChip(tagTag.first)
            }
        }
        MAX_QUESTIONS_COUNT = counter
        question_count_seek_bar.max = (MAX_QUESTIONS_COUNT - MIN_QUESTIONS_COUNT)/STEP_QUESTIONS
    }

    override fun cancelButtonClicked() {

    }
}
