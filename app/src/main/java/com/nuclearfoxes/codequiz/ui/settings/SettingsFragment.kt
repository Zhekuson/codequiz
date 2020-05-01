package com.nuclearfoxes.codequiz.ui.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.button.MaterialButton
import com.nuclearfoxes.codequiz.MainActivity
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.tests.TestsViewModel

class SettingsFragment:Fragment(), LanguageDialogFragment.ConfirmationListener {
    private lateinit var settingsViewModel: SettingsViewModel
    lateinit var sharedPreferences : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        sharedPreferences = activity!!.getSharedPreferences("MAIN",Context.MODE_PRIVATE)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        setupSwitch(root)
        setupLanguageDialog(root)
        return root
    }

    fun setupSwitch(root:View){
        var switch = root.findViewById<Switch>(R.id.theme_switch)
        switch.isChecked = sharedPreferences.getBoolean("DARK_THEME",false)
            switch.setOnCheckedChangeListener { buttonView, isChecked ->
                if(isChecked){
                    sharedPreferences.edit().putBoolean("DARK_THEME", true).commit()
                }else{
                    sharedPreferences.edit().putBoolean("DARK_THEME", false).commit()
                }
                    val intent = Intent(context, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
        }
    }
    fun setupLanguageDialog(root:View){
        var LNGbutton = root.findViewById<MaterialButton>(R.id.change_language_button)
        LNGbutton.setOnClickListener {
            LanguageDialogFragment(this).show(fragmentManager!!,"v")
        }
    }

    override fun confirmButtonClicked() {

    }

    override fun cancelButtonClicked() {

    }

}