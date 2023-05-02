package sva.dungmas.dialogs

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import sva.dungmas.R
import sva.dungmas.game.Game

object DialogCreator { //TODO dividir en clases
    fun createSimpleDialog(message: String, context: Context){
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.simple_message_dialog, null)
        val lbl = view.findViewById<TextView>(R.id.lblSimpleDialog)
        lbl.text = message
        builder.setView(view)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        if (Game.darkMode) {
            dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_bg_dark)
            lbl.setTextColor(context.getColor(R.color.white))
        } else {
            dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_bg)
            lbl.setTextColor(context.getColor(R.color.black))
        }
        dialog.show()
    }

    fun createConfirmDialog(message: String, context: Context, callback: () -> Unit){
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(R.layout.simple_message_dialog, null)
        val lbl = view.findViewById<TextView>(R.id.lblSimpleDialog)
        lbl.text = message
        builder.setView(view)
        builder.setPositiveButton("Ok") { dialog, _ ->
            callback()
        }
        builder.setNegativeButton("No"){ dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        if (Game.darkMode) {
            dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_bg_dark)
            lbl.setTextColor(context.getColor(R.color.white))
        } else {
            dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_bg)
            lbl.setTextColor(context.getColor(R.color.black))
        }
        dialog.show()
    }
}