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
    private val positiveOp: String
): DialogFragment()  {
    var accepted = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        /*val builder = if(Game.darkMode)
            AlertDialog.Builder(activity, R.style.AlertDialogDark)
        else
            AlertDialog.Builder(activity)*/

        val builder = AlertDialog.Builder(activity)

        builder.setTitle(title)
        builder.setMessage(message)
        builder.setNegativeButton(negativeOp){ dialogInterface: DialogInterface?, i: Int ->
            dismiss()
        }
        builder.setPositiveButton(positiveOp){ dialogInterface: DialogInterface?, i: Int ->
            accepted = true
            dismiss()
        }
        val dialog = builder.create()




        return dialog
    }
}