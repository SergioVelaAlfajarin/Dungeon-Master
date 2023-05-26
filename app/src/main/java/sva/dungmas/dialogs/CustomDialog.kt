package sva.dungmas.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment

class CustomDialog(
    private val title: String,
    private val backgroundLayout: View
): DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val builder = AlertDialog.Builder(activity)
        builder.setView(backgroundLayout)
        builder.setTitle(title)
        builder.setPositiveButton("Ok"){ dialogInterface: DialogInterface?, i: Int ->
            dismiss()
        }
        return builder.create()
    }
}