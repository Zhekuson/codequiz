package com.nuclearfoxes.codequiz.ui.tests.custom

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.nuclearfoxes.data.models.tags.Tag

class ChooseTagFragment(var tags:ArrayList<Pair<Tag,Boolean>>) : DialogFragment(), DialogInterface.OnMultiChoiceClickListener {

    interface ConfirmationListener {
        fun confirmButtonClicked()
        fun cancelButtonClicked()
    }

    private lateinit var listener: ConfirmationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = activity as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ConfirmationListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var isChosen :BooleanArray = BooleanArray(tags.size){i->false}
        var names :Array<String> = Array(tags.size){i->""}
        for (i in 0 until tags.size) {
            isChosen[i] = tags[i].second
            names[i] = tags[i].first.name!!
        }
        return AlertDialog.Builder(context!!)
            .setMultiChoiceItems(names,isChosen,this)
            .setTitle("Pick themes")
/*            .setPositiveButton("Confirm") { _, _ ->
                listener.confirmButtonClicked()
            }
            .setNegativeButton("Cancel") { _, _ ->
                listener.cancelButtonClicked()
            }*/
            .create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {
        tags[which] = tags[which].copy(first = tags[which].first,second = isChecked)
    }
}