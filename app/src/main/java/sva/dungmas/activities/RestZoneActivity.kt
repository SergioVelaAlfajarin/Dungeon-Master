package sva.dungmas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import sva.dungmas.R
import sva.dungmas.dialogs.SimpleDialog
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import sva.dungmas.game.items.Storable

class RestZoneActivity : AppCompatActivity() {
    private lateinit var lblLevel: TextView
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var btnRepeatLevel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_zone)

        lblLevel = findViewById(R.id.lblGameLevel)
        updateLblLevel()

        setButtonsEvents()

        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::onActivityResult
        )



        //TODO handle back pressed
        /*onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d(":::", "handleOnBackPressed: ")
                DialogCreator.createConfirmDialog("Salir?", applicationContext) {
                    finish()
                }
            }
        })*/
    }

    private fun onActivityResult(result: ActivityResult){
        when(result.resultCode){
            Codes.BATTLE_LOST.code ->{
                SimpleDialog("info", "has perdido")
                    .show(supportFragmentManager, "info")
                finish()
            }
            Codes.BATTLE_WON.code -> {
                btnRepeatLevel.isEnabled = true
                val itemsDropped: HashMap<Storable, Int> = Game.getLevelDrop()
                //TODO informar al usuario de los objetos dropeados
                //Game.player.addItemsToInventory(itemsDropped)
                updateLblLevel()
            }
        }
    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnLevelInfo))
            .setOnClickListener(this::btnLevelInfoClick)
        (findViewById<Button>(R.id.btnLeaveGame))
            .setOnClickListener(this::btnLeaveGameClick)
        (findViewById<Button>(R.id.btnUpgrade))
            .setOnClickListener(this::btnUpgradeClick)
        (findViewById<Button>(R.id.btnCraft))
            .setOnClickListener(this::btnCraftClick)
        (findViewById<Button>(R.id.btnInventory))
            .setOnClickListener(this::btnInventoryClick)
        (findViewById<Button>(R.id.btnRepeatLevel))
            .setOnClickListener(this::btnRepeatLevelClick)
        (findViewById<Button>(R.id.btnNextLevel))
            .setOnClickListener(this::btnNextLevelClick)


        btnRepeatLevel = findViewById<Button>(R.id.btnRepeatLevel)
        btnRepeatLevel.isEnabled = false
    }

    private fun btnLevelInfoClick(v: View){
        Toast.makeText(this, "Recycler con drop data", Toast.LENGTH_SHORT).show()
    }

    private fun btnLeaveGameClick(v: View){
        Toast.makeText(this, "dialog preguntando seguro quieres salir?", Toast.LENGTH_SHORT).show()
    }

    private fun btnUpgradeClick(v: View){
        val it = Intent(this, UpgradeActivity::class.java)
        launcher.launch(it)
    }

    private fun btnCraftClick(v: View){
        val it = Intent(this, CraftActivity::class.java)
        launcher.launch(it)
    }

    private fun btnInventoryClick(v: View){
        val it = Intent(this, InventoryActivity::class.java)
        launcher.launch(it)
    }

    private fun btnRepeatLevelClick(v: View){
        val it = Intent(this, BattleActivity::class.java)
        val bd = Bundle()
        bd.putBoolean("repeat", true)
        it.putExtras(bd)
        launcher.launch(it)
    }

    private fun btnNextLevelClick(v: View){
        val it = Intent(this, BattleActivity::class.java)
        val bd = Bundle()
        bd.putBoolean("repeat", false)
        it.putExtras(bd)
        launcher.launch(it)
    }

    private fun updateLblLevel() {
        lblLevel.text = getString(R.string.level, Game.level)
    }
}