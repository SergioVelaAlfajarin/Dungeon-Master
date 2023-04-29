package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sva.dungmas.R

class SettingsActivity : AppCompatActivity() {
    private lateinit var diffSwitch: SwitchCompat
    private lateinit var darkSwitch: SwitchCompat
    private lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        diffSwitch = findViewById(R.id.switchDiff)
        darkSwitch = findViewById(R.id.switchDarkMode)
        spinner = findViewById(R.id.spinnerLang)

        fillSpinner()
        setButtonsEvents()
    }

    private fun fillSpinner() {
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf("lang1", "lang2", "lang3")
        )
    }

    private fun setButtonsEvents(){
        (findViewById<FloatingActionButton>(R.id.btnCancelSettings))
            .setOnClickListener(this::btnCancelSettingsClick)
        (findViewById<FloatingActionButton>(R.id.btnSaveSettings))
            .setOnClickListener(this::btnSaveSettingsClick)
    }

    private fun btnCancelSettingsClick(v: View){
        finish()
    }

    private fun btnSaveSettingsClick(v: View){
        val diffActivated = diffSwitch.isChecked
        val darkActivated = darkSwitch.isChecked
        val lang = spinner.selectedItem
        Toast.makeText(this, "Guardado. switch1: $diffActivated, switch2: $darkActivated, lang: $lang", Toast.LENGTH_LONG).show()
        finish()
    }
}