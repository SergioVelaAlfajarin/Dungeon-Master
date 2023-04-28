package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import sva.dungmas.R

class CreatePlayerActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var etNombre: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_player)

        spinner = findViewById(R.id.spinPlayerJob)
        etNombre = findViewById(R.id.etPlayerName)

        fillSpinner()
        setButtonsEvents()
    }

    private fun fillSpinner(){

    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnCreatePlayer))
            .setOnClickListener(this::btnCreatePlayerClick)
    }

    private fun btnCreatePlayerClick(v: View){
        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show()
    }
}