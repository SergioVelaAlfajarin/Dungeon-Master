package sva.dungmas.dialogs

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import sva.dungmas.R

object DialogCreator {
    private val layout = R.layout.simple_message_dialog

    fun createDialog(message: String, context: Context){
        val preferences = context.getSharedPreferences("gamePrefs", Context.MODE_PRIVATE)
        val darkMode = preferences.getBoolean("darkMode", false)
        val builder = AlertDialog.Builder(context)
        val view = LayoutInflater.from(context).inflate(layout, null)
        val lbl = view.findViewById<TextView>(R.id.lblSimpleDialog)
        lbl.text = message
        builder.setView(view)
        builder.setPositiveButton("OK") { dialog, which ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        if(darkMode) {
            dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_bg)
            lbl.setTextColor(context.getColor(R.color.white))
        } else {
            dialog.window?.setBackgroundDrawableResource(R.drawable.simple_dialog_bg_dark)
            lbl.setTextColor(context.getColor(R.color.black))
        }
        dialog.show()
    }
}