package com.nuclearfoxes.codequiz.ui.tests

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.ui.tests.adapters.CloseIconClickListener

class TestsViewModel:ViewModel(), CloseIconClickListener {
    /**
     * list of chosen tags
     */
    lateinit var chosenTagsList: LiveData<ArrayList<String>>

    lateinit var removedTagsList: LiveData<ArrayList<String>>

    lateinit var allAvailableTagsList: ArrayList<String>

    init {
        allAvailableTagsList = ArrayList()
        chosenTagsList = MutableLiveData<ArrayList<String>>()
        removedTagsList = MutableLiveData<ArrayList<String>>()
    }

    override fun onClose(chip: Chip) {
        (chip.parent as ChipGroup).removeView(chip)
    }


}