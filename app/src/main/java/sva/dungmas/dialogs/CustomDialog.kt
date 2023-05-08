package sva.dungmas.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import sva.dungmas.R
import sva.dungmas.game.Game

class CustomDialog(
    private val title: String
): DialogFragment(){

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog{
        val builder = AlertDialog.Builder(activity)
        //val view = LayoutInflater.from(context).inflate(R.layout.simple_message_dialog, null)
        //https://stackoverflow.com/questions/22214243/custom-dialog-fragment
        builder.setTitle(title)
        builder.setPositiveButton("Ok"){ dialogInterface: DialogInterface?, i: Int ->
            dismiss()
        }
        val dialog = builder.create()
        return dialog
    }
}