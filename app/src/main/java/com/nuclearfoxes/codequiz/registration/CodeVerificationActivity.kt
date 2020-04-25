package com.nuclearfoxes.codequiz.registration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.edit
import com.nuclearfoxes.codequiz.MainActivity
import com.nuclearfoxes.codequiz.R
import kotlinx.android.synthetic.main.activity_code_verification.*

class CodeVerificationActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        sharedPreferences = this.getSharedPreferences("MAIN",Context.MODE_PRIVATE)
        setupClickListeners()
    }
    fun setupClickListeners(){
        submit_button.setOnClickListener {
            code_edit_text.text
                //TODO add api call
            if(true){
                var intent1 = Intent(this, MainActivity::class.java)
                intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                var editor = sharedPreferences.edit()
                editor.putString("EMAIL", intent.getStringExtra("EMAIL"))
                editor.putString("JWT", "vsevdvfv")
                editor.apply()
                startActivity(intent1)
            }else{
                error_text_view.visibility = View.VISIBLE
                error_text_view.text = "Wrong code!"
            }
        }
    }
}
