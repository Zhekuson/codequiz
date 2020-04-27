package com.nuclearfoxes.codequiz.ui.settings

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

class LanguageDialogFragment(var fragment:Fragment):DialogFragment() {
    interface ConfirmationListener {
        fun confirmButtonClicked()
        fun cancelButtonClicked()
    }
    private lateinit var listener: LanguageDialogFragment.ConfirmationListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            // Instantiate the ConfirmationListener so we can send events to the host
            listener =  fragment as LanguageDialogFragment.ConfirmationListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException(activity.toString() + " must implement ConfirmationListener")
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var languages:Array<String> = arrayOf("English", "Russian")
/*        languages[0]=("English")
        languages[1]=("Russian")*/
        return AlertDialog.Builder(context!!)
            //.setSingleChoiceItems(languages, 0,{ dialog, which -> })
            .setMessage("Choose language")
            .setSingleChoiceItems(languages,0,{ dialog, which ->
                dialog.dismiss()

            })
          /*  .setPositiveButton("Confirm") { _, _ ->
                listener.confirmButtonClicked()
            }
            .setNegativeButton("Cancel") { _, _ ->
                listener.cancelButtonClicked()
            }*/
            .create()
    }
}
