package com.nuclearfoxes.codequiz.quiz

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.nuclearfoxes.codequiz.R
import com.nuclearfoxes.data.models.tags.Tag

class ConfirmFinishFragment () : DialogFragment(), DialogInterface.OnMultiChoiceClickListener {

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

            return AlertDialog.Builder(context!!).setTitle(getString(R.string.finish_quiz))
                .setPositiveButton("Ok") { _, _ ->
                    listener.confirmButtonClicked()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    listener.cancelButtonClicked()
                }
                .create()
        }

    override fun onClick(dialog: DialogInterface?, which: Int, isChecked: Boolean) {

    }
}