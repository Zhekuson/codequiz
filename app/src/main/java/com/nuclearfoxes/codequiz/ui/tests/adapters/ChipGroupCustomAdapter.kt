package com.nuclearfoxes.codequiz.ui.tests.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.data.models.tags.Tag

class ChipGroupCustomAdapter(var mContext:Context, var chipGroup: ChipGroup,
                             var tags:ArrayList<Tag>, closeIconClickListener: CloseIconClickListener) {
    init {
        for (tag in tags){
            chipGroup.addView(ChipFactory.getChip(mContext, tag, closeIconClickListener))
        }
    }
    fun updateChipGroup(){
        //chipGroup.removeAllViews()

    }
    fun removeChip(){

    }
    fun addChip(){

    }
}