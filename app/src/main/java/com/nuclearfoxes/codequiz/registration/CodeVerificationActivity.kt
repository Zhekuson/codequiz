package com.nuclearfoxes.codequiz.registration

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import com.nuclearfoxes.codequiz.MainActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.api.UserRequests
import com.nuclearfoxes.data.exceptions.TooMuchAttemptsException
import com.nuclearfoxes.data.exceptions.VerificationException
import kotlinx.android.synthetic.main.activity_code_verification.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.*
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class CodeVerificationActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var coroutineExceptionHandler:CoroutineExceptionHandler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_code_verification)
        sharedPreferences = this.getSharedPreferences("MAIN",Context.MODE_PRIVATE)
         coroutineExceptionHandler =
            CoroutineExceptionHandler { _,t->
                /*Toast.makeText(this@CodeVerificationActivity,"Error happened, try again",
                    Toast.LENGTH_LONG).show()*/
                try {
                    MainScope().launch {
                        code_verification_progress_bar.visibility = View.INVISIBLE
                        error_text_view.visibility = View.VISIBLE
                        if (t is TooMuchAttemptsException) {
                            error_text_view.text =
                                "Too much attempts, retry sending email(press back button & try input email again)"
                        }
                        if (t is VerificationException) {
                            error_text_view.text = "Verification error: Wrong code"
                        }
                        if(t is SocketTimeoutException){
                            error_text_view.text = "Error: request took too long"
                        }
                        if(t is UnknownHostException){
                            error_text_view.text = "Error: no internet connection"
                        }
                    }
                }catch (e:Exception){

                }
            }
        setupClickListeners()
    }
    fun setupClickListeners(){
        submit_button.setOnClickListener {
            val uiScope: CoroutineScope = CoroutineScope(coroutineExceptionHandler)
            code_verification_progress_bar.visibility = View.VISIBLE
                uiScope.launch(Dispatchers.Default) {
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
                    MainScope().launch {
                    code_verification_progress_bar.visibility = View.INVISIBLE
                        }
                    startActivity(intent1)
                }
        }
    }
}
