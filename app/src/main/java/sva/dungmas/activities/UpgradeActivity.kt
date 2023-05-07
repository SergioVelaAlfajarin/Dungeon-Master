package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.entities.Armor

class UpgradeActivity : AppCompatActivity() {
    private lateinit var playerArmor: Armor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade)
        playerArmor = Game.player.armor
        (findViewById<TextView>(R.id.lblArmorLvl))
            .text = getString(R.string.level, playerArmor.level)
    }
}