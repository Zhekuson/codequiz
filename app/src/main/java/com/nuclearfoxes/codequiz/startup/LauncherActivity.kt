package com.nuclearfoxes.codequiz.startup

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nuclearfoxes.codequiz.MainActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.registration.RegistrationActivity

class LauncherActivity : AppCompatActivity() {

    val settingsName = "MAIN"
    lateinit var sharedPreferences:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        sharedPreferences = this.getSharedPreferences(settingsName,Context.MODE_PRIVATE)
    }

    override fun onStart() {
        var intent= when(sharedPreferences.getString("EMAIL", "?")){
            "?"-> Intent(this, RegistrationActivity::class.java)
            else-> Intent(this, MainActivity::class.java)
        }

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        super.onStart()
    }
}
