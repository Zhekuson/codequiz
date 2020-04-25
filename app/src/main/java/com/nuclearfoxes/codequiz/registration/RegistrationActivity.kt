package com.nuclearfoxes.codequiz.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.button.MaterialButton
import com.nuclearfoxes.codequiz.R
import kotlinx.android.synthetic.main.activity_code_verification.*
import kotlinx.android.synthetic.main.activity_registration.*

class RegistrationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        findViewById<MaterialButton>(R.id.verify_email_button).setOnClickListener {
            email_edit_text.text
            //TODO add api call
            var intent1 = Intent(this, CodeVerificationActivity::class.java)
            intent1.putExtra("EMAIL", "edplyusch@edu.hse.ru")
            startActivity(intent1)
        }
    }


}
