package com.nuclearfoxes.codequiz.ui.tests.adapters

import android.content.Context
import android.content.ContextWrapper
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.TintTypedArray.obtainStyledAttributes
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.tags.Tag
import java.util.zip.Inflater


object ChipFactory {
    fun getChip(mContext: Context, tag: Tag, closeIconClickListener:CloseIconClickListener): Chip{
        val inflater =
            mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var chip = inflater.inflate(R.layout.single_chip_layout, null) as Chip
        chip.text = tag.name
        chip.setOnCloseIconClickListener{
            closeIconClickListener.onClose(it as Chip)
        }
        return chip
    }
    fun getAddChip(context: Context):Chip{
        var chip = Chip(ContextThemeWrapper(context, R.style.ThemeAddChipStyle))
        chip.text = context.getString(R.string.add)
        return chip
    }

}