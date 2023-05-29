package sva.dungmas.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class BattleLostDialog(
    private val title: String,
    private val message: String,
    private val positiveOp: String,
    private val callback: ConfirmCallback
): DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(positiveOp){ dialogInterface: DialogInterface?, i: Int ->
            callback.dialogOk()
            dismiss()
        }
        return builder.create()
    }
}