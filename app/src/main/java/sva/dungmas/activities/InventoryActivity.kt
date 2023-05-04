package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.entities.Inventory

class InventoryActivity : AppCompatActivity() {
    private lateinit var playerInv: Inventory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        playerInv = Game.player.inventory
    }
}