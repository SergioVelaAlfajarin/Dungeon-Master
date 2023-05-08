package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.widget.SwitchCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game

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
        updateComponents()
    }

    private fun updateComponents() {
        diffSwitch.isChecked = Game.easyMode
        darkSwitch.isChecked = Game.darkMode
    }

    private fun fillSpinner() {
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            arrayOf(getString(R.string.spa), getString(R.string.eng))
        )
        spinner.selectedItem = when(Game.lang){
            "en" -> getString(R.string.eng)
            else -> getString(R.string.spa)
        }
    }

    private fun setButtonsEvents(){
        (findViewById<FloatingActionButton>(R.id.btnCancelSettings))
            .setOnClickListener(this::btnCancelSettingsClick)
        (findViewById<FloatingActionButton>(R.id.btnSaveSettings))
            .setOnClickListener(this::btnSaveSettingsClick)
    }

    private fun btnCancelSettingsClick(v: View){
        setResult(Codes.OK.code)
        finish()
    }

    private fun btnSaveSettingsClick(v: View){
        Game.easyMode = diffSwitch.isChecked
        Game.darkMode = darkSwitch.isChecked
        Game.lang = when(spinner.selectedItem){
            getString(R.string.spa) -> "es"
            getString(R.string.eng) -> "en"
            else -> ""
        }
        setResult(Codes.UPDATE_SETTINGS.code)
        finish()
    }
}

class miClase: Int(){
    val si: String = ""

    fun hola(){

    }
}