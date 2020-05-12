package com.nuclearfoxes.codequiz.ui.tests

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.ui.tests.adapters.CloseIconClickListener

class TestsViewModel:ViewModel(), CloseIconClickListener {

    override fun onClose(chip: Chip) {
        (chip.parent as ChipGroup).removeView(chip)
    }


}