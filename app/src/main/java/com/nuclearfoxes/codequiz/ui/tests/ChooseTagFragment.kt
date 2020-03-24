package com.nuclearfoxes.codequiz.ui.tests

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ChooseTagFragment : DialogFragment() {

    interface ConfirmationListener {
        fun confirmButtonClicked()
        fun cancelButtonClicked()
    }

    private lateinit var listener: ConfirmationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // Instantiate the ConfirmationListener so we can send events to the host
            listener = activity as ConfirmationListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(activity.toString() + " must implement ConfirmationListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
         var tags = ArrayList<String>()
        tags.add("Classes")
        tags.add("Exceptions")
        tags.add("Classe")
        tags.add("Classs")
        tags.add("Claes")
        tags.add("Csses")
        tags.add("IClasses")
        var bools = BooleanArray(7)
        return AlertDialog.Builder(context!!)
            //.setMultiChoiceItems(tags,bools,)
            .setMessage("Pick themes")
            .setPositiveButton("Confirm") { _, _ ->
                listener.confirmButtonClicked()
            }
            .setNegativeButton("Cancel") { _, _ ->
                listener.cancelButtonClicked()
            }
            .create()
    }
}