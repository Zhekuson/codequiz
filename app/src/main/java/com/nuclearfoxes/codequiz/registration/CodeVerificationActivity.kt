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
import com.nuclearfoxes.data.api.UserRequests
import com.nuclearfoxes.data.exceptions.VerificationException
import kotlinx.android.synthetic.main.activity_code_verification.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

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
            try {
                GlobalScope.launch {
                    code_verification_progress_bar.visibility = View.VISIBLE
                    var JWT = UserRequests.verifyEmail(
                        intent.getIntExtra("SESSIONID", 0),
                        code_edit_text.text.toString().toInt(),
                        intent.getStringExtra("EMAIL")
                    )
                    var intent1 = Intent(this@CodeVerificationActivity, MainActivity::class.java)
                    intent1.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    var editor = sharedPreferences.edit()
                    editor.putString("EMAIL", intent.getStringExtra("EMAIL"))
                    editor.putString("JWT", JWT)
                    editor.apply()
                    code_verification_progress_bar.visibility = View.INVISIBLE
                    startActivity(intent1)
                }
            }catch (e:VerificationException){
                error_text_view.visibility = View.VISIBLE
                error_text_view.text = "Verification went wrong"
            }
            catch (e:Exception){
                error_text_view.visibility = View.VISIBLE
                error_text_view.text = "Something went wrong, try again"
            }

        }
    }
}
