package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.entities.Armor
import sva.dungmas.recyclers.RecipeRecyclerAdapter

class UpgradeActivity : AppCompatActivity() {
    private lateinit var playerArmor: Armor
    private lateinit var imgArmorIcon: ImageView
    private lateinit var lblArmorLevel: TextView
    private lateinit var recyclerUpgradeArmor: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upgrade)
        playerArmor = Game.player.armor
        imgArmorIcon = findViewById(R.id.imgArmorIcon)
        lblArmorLevel = findViewById(R.id.lblArmorLvl)
        recyclerUpgradeArmor = findViewById(R.id.recyclerUpgradeArmor)
        recyclerUpgradeArmor.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerUpgradeArmor.adapter = RecipeRecyclerAdapter(playerArmor.getRequirementsForNextLevel())

        updateLabel()
        updateIcon()

        (findViewById<Button>(R.id.btnUpgradeArmor))
            .setOnClickListener(this::btnUpgradeArmorClick)
    }

    private fun updateIcon() = imgArmorIcon.setImageResource(playerArmor.icon)

    private fun updateLabel() {
        lblArmorLevel.text = getString(R.string.level, playerArmor.level)
    }

    private fun btnUpgradeArmorClick(v: View){
        //TODO HACER REQUESITOS (recipe)
        //TODO CREAR LABEL QUE MUESTRE STATS ACTUALES
        playerArmor.increaseLevel()
        updateIcon()
        updateLabel()
        recyclerUpgradeArmor.adapter = RecipeRecyclerAdapter(playerArmor.getRequirementsForNextLevel())
    }
}