package com.nuclearfoxes.codequiz.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.api.UserRequests
import com.nuclearfoxes.data.exceptions.InternalServerErrorException
import com.nuclearfoxes.data.exceptions.MailException
import kotlinx.android.synthetic.main.activity_code_verification.*
import kotlinx.android.synthetic.main.activity_registration.*
import kotlinx.coroutines.*
import kotlinx.coroutines.android.awaitFrame
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        var coroutineExceptionHandler =
            CoroutineExceptionHandler { _,t->
                //Toast.makeText(this@RegistrationActivity,"Error happened, try again",
                  //  Toast.LENGTH_LONG).show()
                try {
                    MainScope().launch {
                        registration_progress_bar.visibility = View.INVISIBLE
                        registration_error_text_view.visibility = View.VISIBLE
                        if (t is MailException) {
                            registration_error_text_view.text = "Probably, wrong email?"
                        } else if (t is SocketTimeoutException) {
                            registration_error_text_view.text = "Request took too long, try again"
                        } else if (t is InternalServerErrorException) {
                            registration_error_text_view.text = "Internal server error"
                        } else if (t is UnknownHostException) {
                            registration_error_text_view.text = "Error: no internet connection"
                        }
                    }
                }catch (e:Exception){
                      var i =0
                }
        }
        findViewById<MaterialButton>(R.id.verify_email_button).setOnClickListener {
                var sessionId:Int = 0
                val uiScope:CoroutineScope = CoroutineScope(coroutineExceptionHandler)
                registration_progress_bar.visibility = View.VISIBLE
                registration_error_text_view.visibility = View.INVISIBLE
                uiScope.launch(Dispatchers.Default) {
                        sessionId =
                            UserRequests.sendVerificationEmail(email_edit_text.text.toString())
                        var intent1 =
                            Intent(this@RegistrationActivity, CodeVerificationActivity::class.java)
                        intent1.putExtra("EMAIL", email_edit_text.text.toString())
                        intent1.putExtra("SESSIONID", sessionId)
                        registration_progress_bar.visibility = View.INVISIBLE
                        startActivity(intent1)
                }

        }
    }


}
