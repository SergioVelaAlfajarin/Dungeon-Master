package sva.dungmas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import sva.dungmas.R
import sva.dungmas.dialogs.ConfirmCallback
import sva.dungmas.dialogs.ConfirmDialog
import sva.dungmas.dialogs.CustomDialog
import sva.dungmas.dialogs.SimpleDialog
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import sva.dungmas.game.items.ItemPart
import sva.dungmas.recyclers.PreviewRecyclerAdapter

class RestZoneActivity : AppCompatActivity() {
    private lateinit var lblLevel: TextView
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private lateinit var btnRepeatLevel: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rest_zone)

        lblLevel = findViewById(R.id.lblGameLevel)
        launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            this::onActivityResult
        )

        updateLblLevel()
        setButtonsEvents()

        onBackPressedDispatcher.addCallback(object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                confirmExit()
            }
        })
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
                val itemsDropped: LinkedHashMap<ItemPart, Int> = Game.getLevelDrop()

                Game.player.inventory.addItemsDropped(itemsDropped)

                Snackbar.make(
                    findViewById(R.id.restZoneLayout),
                    R.string.lvlCompletedMsg,
                    Snackbar.LENGTH_SHORT
                ).show()

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

        btnRepeatLevel = findViewById(R.id.btnRepeatLevel)
        btnRepeatLevel.isEnabled = false
    }

    private fun btnLevelInfoClick(v: View){
        val view = layoutInflater.inflate(R.layout.preview_dialog, null)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerItemPreview)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = PreviewRecyclerAdapter()

        CustomDialog(getString(R.string.itemPreviewDialogTitle, Game.level + 1), view)
            .show(supportFragmentManager, ":::")
    }

    private fun btnLeaveGameClick(v: View){
        confirmExit()
    }

    private fun confirmExit() = ConfirmDialog(
        getString(R.string.exitGameTitle),
        getString(R.string.exitGameMsg),
        getString(R.string.cancelOp),
        getString(R.string.exitOp),
        object : ConfirmCallback {
            override fun dialogOk() {
                finish()
                Game.reset()
            }
        }
    ).show(supportFragmentManager, ":::")

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