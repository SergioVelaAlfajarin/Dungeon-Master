package sva.dungmas.activities

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.edit
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sva.dungmas.R
import java.util.Locale

class SettingsActivity : AppCompatActivity() {
    private lateinit var diffSwitch: SwitchCompat
    private lateinit var darkSwitch: SwitchCompat
    private lateinit var spinner: Spinner
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        diffSwitch = findViewById(R.id.switchDiff)
        darkSwitch = findViewById(R.id.switchDarkMode)
        spinner = findViewById(R.id.spinnerLang)
        preferences = getSharedPreferences("gamePrefs", Context.MODE_PRIVATE)

        fillSpinner()
        setButtonsEvents()
        updateComponents()
    }

    private fun updateComponents() {
        val easyMode = preferences.getBoolean("easyMode", false)
        val darkMode = preferences.getBoolean("darkMode", false)
        diffSwitch.isChecked = easyMode
        darkSwitch.isChecked = darkMode
    }

    private fun fillSpinner() {
        val lang = arrayOf(
            getString(R.string.spa),
            getString(R.string.eng)
        )
        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            lang
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
        updatePref(diffSwitch.isChecked, darkSwitch.isChecked, spinner.selectedItem.toString())
        finish()
    }

    private fun updatePref(easyMode: Boolean, darkMode: Boolean, langSpinner: String) {
        preferences.edit {
            putBoolean("easyMode", easyMode)
            putBoolean("darkMode", darkMode)
            putString ("lang", langSpinner)
        }
        if(darkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        val locale = when(langSpinner){
            getString(R.string.spa) -> Locale("sp")
            getString(R.string.eng) -> Locale("en")
            else -> Locale.getDefault()
        }
        /*
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        applicationContext.createConfigurationContext(config)*/
    }
}