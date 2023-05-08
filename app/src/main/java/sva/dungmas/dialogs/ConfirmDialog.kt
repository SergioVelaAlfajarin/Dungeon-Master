package sva.dungmas.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import sva.dungmas.R
import sva.dungmas.game.Game

class ConfirmDialog(
    private val title: String,
    private val message: String,
    private val negativeOp: String,
    private val positiveOp: String,
    private val callback: ConfirmCallback
): DialogFragment()  {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeOp){ dialogInterface: DialogInterface?, i: Int ->
            dismiss()
        }
        builder.setPositiveButton(positiveOp){ dialogInterface: DialogInterface?, i: Int ->
            callback.dialogOk()
            dismiss()
        }
        return builder.create()
    }
}

interface ConfirmCallback{
    fun dialogOk()
}