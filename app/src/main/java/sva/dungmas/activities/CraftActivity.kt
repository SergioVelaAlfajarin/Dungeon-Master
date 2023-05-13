package sva.dungmas.activities

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.items.Item
import sva.dungmas.recyclers.CraftRecyclerAdapter
import sva.dungmas.recyclers.RecipeRecyclerAdapter
import kotlin.streams.toList

class CraftActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: CraftRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeRecyclerView: RecyclerView
    private lateinit var btnCraft: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craft)

        btnCraft = findViewById(R.id.btnCraftItem)
        recyclerView = findViewById(R.id.recyclerCraftView)
        recipeRecyclerView = findViewById(R.id.recyclerUpgradeArmor)
        recyclerAdapter = CraftRecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        btnCraft.setOnClickListener(this::btnCraftClick)
    }

    private fun btnCraftClick(v: View) {
        //only clickable if enough items in inventory
        val item = recyclerAdapter.selectedItem
        val inv = Game.player.inventory
        item.recipe.forEach{(key, value) ->
            inv.remove(key, value)
        }
        inv.add(item)
        recyclerAdapter.notifyDataSetChanged()
        updateRecipeRecycler()
        updateCraftButton(inv.checkIfItemIsCraftable(item))
    }

    fun updateRecipeRecycler(){
        val item = recyclerAdapter.selectedItem
        recipeRecyclerView.adapter = RecipeRecyclerAdapter(item.recipe)
    }

    fun updateCraftButton(craftable: Boolean) {
        btnCraft.isEnabled = craftable
    }
}