package com.nuclearfoxes.codequiz.ui.settings

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.Preference
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.codequiz.ui.tests.TestsViewModel

class SettingsFragment:Fragment() {
    private lateinit var settingsViewModel: SettingsViewModel
    lateinit var sharedPreferences : SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        settingsViewModel =
            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
        sharedPreferences = activity!!.getPreferences(Context.MODE_PRIVATE)
        val root = inflater.inflate(R.layout.fragment_settings, container, false)
        setupSwitch(root)
        return root
    }

    fun setupSwitch(root:View){
        var switch = root.findViewById<Switch>(R.id.theme_switch)
        switch.setOnCheckedChangeListener { buttonView, isChecked ->//TODO theme switcjh
        }
    }
}