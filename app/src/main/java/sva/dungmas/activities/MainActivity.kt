package sva.dungmas.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatDelegate
import sva.dungmas.R
import sva.dungmas.bbdd.BDManager
import sva.dungmas.dialogs.SimpleDialog
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Game.init(
            applicationContext.getSharedPreferences("gamePrefs", Context.MODE_PRIVATE)
        )
        setButtonsEvents()
        updateSettings()

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::onActivityResult
        )
    }

    private fun onActivityResult(result: ActivityResult){
        when(result.resultCode){
            Codes.ERR.code ->{
                SimpleDialog("info", "error")
                    .show(supportFragmentManager, "info")
            }
            Codes.OK.code -> Log.d(":::", "onActivityResult: FINISHED ACTIVITY OK")
            Codes.UPDATE_SETTINGS.code ->{
                updateSettings()
                recreate() //TODO does not work (used to change lang)
                //finish()
                //startActivity(intent)
            }
        }
    }

    private fun updateSettings(){
        if(Game.darkMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        val locale = Locale(Game.lang)
        Locale.setDefault(locale)
        val configuration = resources.configuration
        configuration.setLocale(locale)
        createConfigurationContext(configuration)
    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnStartGame))
           .setOnClickListener(this::btnStartGameClick)
        (findViewById<Button>(R.id.btnRanking))
            .setOnClickListener(this::btnRankingClick)
        (findViewById<Button>(R.id.btnSettings))
            .setOnClickListener(this::btnSettingsClick)
    }

    private fun btnStartGameClick(v: View){
        val it = Intent(this, CreatePlayerActivity::class.java)
        launcher.launch(it)
    }

    private fun btnRankingClick(v: View){
        val it = Intent(this, RankingActivity::class.java)
        launcher.launch(it)
    }

    private fun btnSettingsClick(v: View){
        val it = Intent(this, SettingsActivity::class.java)
        launcher.launch(it)
    }
}