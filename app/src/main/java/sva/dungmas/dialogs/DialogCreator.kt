package sva.dungmas.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import sva.dungmas.R

object DialogCreator {
    private val layout = R.layout.simple_message_dialog

    fun createDialog(message: String, context: Context){
        createDialog(message, context, layout)
    }

    fun createDialog(message: String, context: Context, customLayout: Int){
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(customLayout, null)
        (view.findViewById<TextView>(R.id.lblSimpleDialog)).text = message
        builder.setView(view)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_background)
        dialog.show()
    }
}