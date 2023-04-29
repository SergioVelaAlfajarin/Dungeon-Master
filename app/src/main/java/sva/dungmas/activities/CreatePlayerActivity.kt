package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import sva.dungmas.R
import sva.dungmas.dialogs.DialogCreator

class CreatePlayerActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_player)

        spinner = findViewById(R.id.spinPlayerJob)
        etName = findViewById(R.id.etPlayerName)

        fillSpinner()
        setButtonsEvents()
    }

    private fun fillSpinner(){
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf("Item 1", "Item 2", "Item 3")
        )
    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnCreatePlayer))
            .setOnClickListener(this::btnCreatePlayerClick)
    }

    private fun btnCreatePlayerClick(v: View){
        val spinnerText: String = spinner.selectedItem.toString()
        val etText: String = etName.text.toString()

        if(etText == ""){
            DialogCreator.createDialog("et vacio", this)
        }

        Toast.makeText(
            this,
            "spinner: $spinnerText, et: $etText",
            Toast.LENGTH_SHORT
        ).show()
    }
}