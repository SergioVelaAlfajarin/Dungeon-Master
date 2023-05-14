package sva.dungmas.activities

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.recyclers.CraftRecyclerAdapter
import sva.dungmas.recyclers.RecipeRecyclerAdapter

class CraftActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: CraftRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeRecyclerView: RecyclerView
    private lateinit var btnCraft: Button
    private lateinit var btnCraftMax: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craft)

        btnCraft = findViewById(R.id.btnCraftItem)
        btnCraftMax = findViewById(R.id.btnCraftMax)
        recyclerView = findViewById(R.id.recyclerCraftView)
        recipeRecyclerView = findViewById(R.id.recyclerUpgradeArmor)
        recyclerAdapter = CraftRecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnCraft.setOnClickListener(this::btnCraftClick)
        btnCraftMax.setOnClickListener(this::btnCraftMaxClick)
    }

    private fun btnCraftMaxClick(view: View) {
        TODO("Not yet implemented")
    }

    private fun btnCraftClick(v: View) {
        //only clickable if enough items in inventory
        val item = recyclerAdapter.selectedItem
        val inv = Game.player.inventory
        item.recipe.forEach { (key, value) ->
            inv.remove(key, value)
        }
        inv.add(item)
        recyclerAdapter.notifyDataSetChanged()
        updateRecipeRecycler()
        updateCraftButton(inv.checkIfItemIsCraftable(item))
    }

    fun updateRecipeRecycler() {
        val item = recyclerAdapter.selectedItem
        recipeRecyclerView.adapter = RecipeRecyclerAdapter(item.recipe)
    }

    fun updateCraftButton(craftable: Boolean) {
        btnCraft.isEnabled = craftable
    }
}