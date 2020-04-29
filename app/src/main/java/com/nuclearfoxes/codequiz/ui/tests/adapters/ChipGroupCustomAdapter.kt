package com.nuclearfoxes.codequiz.ui.tests.adapters

import android.content.Context
import android.view.LayoutInflater
import androidx.lifecycle.LiveData
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.data.models.tags.Tag
import com.nuclearfoxes.data.models.tags.TagCountPair

class ChipGroupCustomAdapter(var mContext:Context, var chipGroup: ChipGroup,
                             var tags:ArrayList<TagCountPair>, var closeIconClickListener: CloseIconClickListener) {
    init {
        for (tagCountPair in tags){
            chipGroup.addView(ChipFactory.getChip(mContext,
                tagCountPair.tag, closeIconClickListener))
        }
    }
    fun removeAllExceptAdd(){
        chipGroup.removeViews(1,chipGroup.childCount-1)
    }
    fun addChip(tagCountPair: TagCountPair){
        chipGroup.addView(ChipFactory.getChip(mContext, tagCountPair.tag, closeIconClickListener))
    }
}