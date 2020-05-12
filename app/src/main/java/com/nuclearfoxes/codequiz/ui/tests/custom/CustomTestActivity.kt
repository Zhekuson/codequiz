package com.nuclearfoxes.codequiz.ui.tests.custom

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.quiz.QuizActivity
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipFactory
import com.nuclearfoxes.codequiz.ui.tests.adapters.ChipGroupCustomAdapter
import com.nuclearfoxes.codequiz.ui.tests.adapters.CloseIconClickListener
import com.nuclearfoxes.codequiz.ui.tests.loading.QuizLoadingActivity
import com.nuclearfoxes.data.api.TagsRequests
import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.models.tags.Tag
import com.nuclearfoxes.data.models.tags.TagCountPair
import kotlinx.android.synthetic.main.activity_custom_test.*
import kotlinx.coroutines.*
import java.net.UnknownHostException
import kotlin.coroutines.coroutineContext

class CustomTestActivity : AppCompatActivity(), CloseIconClickListener,
    ChooseTagFragment.ConfirmationListener {
    var MAX_QUESTIONS_COUNT = 0
    var MIN_QUESTIONS_COUNT = 1
    val STEP_QUESTIONS = 1
    val MAX_TIME:Int = 60
    val MIN_TIME:Int = 1
    val STEP_TIME = 1
    var tagCountPairs: ArrayList<TagCountPair> = ArrayList()
    lateinit var sharedPreferences: SharedPreferences
    var tagsTags:ArrayList<Pair<TagCountPair,Boolean>> = ArrayList()
    lateinit var adapter:ChipGroupCustomAdapter
    var coroutineExceptionHandler = CoroutineExceptionHandler{_ , t->
        MainScope().launch {
            if (t is UnknownHostException) {
                Toast.makeText(this@CustomTestActivity,
                    "Error: no internet connection",Toast.LENGTH_LONG).show()
            }
            else if(t is InternalServerErrorException){
                Toast.makeText(this@CustomTestActivity,
                    "Internal server error",Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = this.getSharedPreferences("MAIN", Context.MODE_PRIVATE)
        if(sharedPreferences.getBoolean("DARK_THEME",false)){
            setTheme(R.style.DarkAppTheme)
        }
        setContentView(R.layout.activity_custom_test)
        MAX_QUESTIONS_COUNT = 0
        GlobalScope.launch(Dispatchers.Main) {
            max_questions_progress_bar.visibility = View.VISIBLE
            var uiScope = CoroutineScope(coroutineExceptionHandler)
            uiScope.launch(Dispatchers.IO) {
                tagCountPairs =
                    TagsRequests.getTagCountPairs(sharedPreferences.getString("JWT", ""))
            }
            max_questions_progress_bar.visibility = View.INVISIBLE
        }
        this.getSupportActionBar()?.hide()
        setupAdaptersAndClickListeners()
    }
    fun setupAdaptersAndClickListeners(){
        go_button.setOnClickListener{
            val intentNext = Intent(this, QuizLoadingActivity::class.java)
            if(MAX_QUESTIONS_COUNT != 0) {
                intentNext.putExtra("MODE", "CUSTOM")
                intentNext.putExtra("MINUTES", time_seek_bar_tests.progress + 1)
                intentNext.putExtra("QUESTIONS_COUNT", question_count_seek_bar.progress + 1)
                var tags: ArrayList<Tag> = ArrayList()
                for (tagTag in tagsTags) {
                    if (tagTag.second) {
                        tags.add(tagTag.first.tag)
                    }
                }
                intentNext.putExtra("TAGS", tags)
                startActivity(intentNext)
            }
            else{
                Toast.makeText(this,"No questions selected", Toast.LENGTH_LONG).show()
            }
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
                questions_count_textView.text = (MIN_QUESTIONS_COUNT + progress*STEP_QUESTIONS).toString() + "/${MAX_QUESTIONS_COUNT}"+ " questions"
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
        var tags:ArrayList<Tag> = ArrayList()
        for(i in 0 until tagsTags.size) {
            if (tagsTags[i].first.tag.name == chip.text) {
                tagsTags[i] = tagsTags[i].copy(first = tagsTags[i].first, second = false)
            }
            if(tagsTags[i].second){
                tags.add(tagsTags[i].first.tag)
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            max_questions_progress_bar.visibility = View.VISIBLE
            var uiScope = CoroutineScope(coroutineExceptionHandler)
            uiScope.launch(Dispatchers.IO) {
                MAX_QUESTIONS_COUNT = TagsRequests.getMaxCountQuestions(
                    sharedPreferences.getString("JWT", ""), tags
                )

                question_count_seek_bar.max =
                    (MAX_QUESTIONS_COUNT - MIN_QUESTIONS_COUNT) / STEP_QUESTIONS
            }
            max_questions_progress_bar.visibility = View.INVISIBLE
        }
    }

    override fun confirmButtonClicked() {
        adapter.removeAllExceptAdd()
        var tags:ArrayList<Tag> = ArrayList()

        for (tagTag in tagsTags){
            if(tagTag.second){
                tags.add(tagTag.first.tag)
                adapter.addChip(tagTag.first)
            }
        }
        max_questions_progress_bar.visibility = View.VISIBLE
        GlobalScope.launch(Dispatchers.Main){
            var uiScope = CoroutineScope(coroutineExceptionHandler)
            uiScope.launch(Dispatchers.IO) {
                MAX_QUESTIONS_COUNT = TagsRequests.getMaxCountQuestions(
                    sharedPreferences.getString("JWT", ""), tags
                )

                question_count_seek_bar.max =
                    (MAX_QUESTIONS_COUNT - MIN_QUESTIONS_COUNT) / STEP_QUESTIONS
            }
        }
        max_questions_progress_bar.visibility = View.INVISIBLE

    }

    override fun cancelButtonClicked() {

    }
}
