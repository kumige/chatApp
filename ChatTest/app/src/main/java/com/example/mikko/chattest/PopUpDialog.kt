/*
Mikko Romo
1606176

Creates a dialog for the user list and message history.
*/
package com.example.mikko.chattest

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.util.Log

class PopUpDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val args = arguments
            val str = args?.getString("content")
            Log.d(TAG1, "USERLIST HERE: $str")

            builder.setMessage(str)
                    .setNegativeButton("exit"
                    ) { _, _ ->
                        // User cancelled the dialog
                    }
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
