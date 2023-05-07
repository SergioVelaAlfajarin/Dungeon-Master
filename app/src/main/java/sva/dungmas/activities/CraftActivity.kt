package sva.dungmas.activities

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import sva.dungmas.game.entities.Inventory
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import kotlin.streams.toList

class CraftActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: CraftRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var recipeRecyclerView: RecyclerView
    private lateinit var btnCraft: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO activar fabricar objeto si check fabricable is enabled
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_craft)

        btnCraft = findViewById(R.id.btnCraftItem)
        recyclerView = findViewById(R.id.recyclerCraftView)
        recipeRecyclerView = findViewById(R.id.recyclerRecipes)
        recyclerAdapter = CraftRecyclerAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recipeRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
    }

    fun updateRecipeRecycler(){
        val item = recyclerAdapter.selectedItem
        recipeRecyclerView.adapter = RecipeRecyclerAdapter(item)
    }

    private fun updateCraftButton(craftable: Boolean) {
        btnCraft.isEnabled = craftable
    }

    //-----------------------------

    class CraftRecyclerAdapter(val context: Context): RecyclerView.Adapter<CraftViewHolder>(){

        private val item = Game.bdManager.getCraftableItems()
        private var selectedPos: Int = -1

        val selectedItem: Item
            get(){
                return item[selectedPos]
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CraftViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.craft_list_item, parent, false)
            return CraftViewHolder(view)
        }

        override fun getItemCount(): Int {
            return item.size
        }

        override fun onBindViewHolder(holder: CraftViewHolder, position: Int) {
            val text = context.getString(R.string.quantity, Game.player.inventory[item[position]])
            val isCraftable: Boolean = Game.player.inventory.checkIfItemIsCraftable(item[position])
            with(holder){
                imgItem.setImageResource(item[position].iconResId)
                lblItemName.text = item[position].name
                lblItemQnty.text = text
                checkCraftable.isChecked = isCraftable
                checkCraftable.isEnabled = false

                if(selectedPos == position){
                    itemView.setBackgroundColor(Color.GRAY)
                }else{
                    itemView.setBackgroundColor(Color.TRANSPARENT)
                }

                itemView.setOnClickListener{
                    selectedPos = holder.adapterPosition
                    notifyDataSetChanged()

                    context as CraftActivity
                    context.updateRecipeRecycler()
                    context.updateCraftButton(isCraftable)
                }
            }
        }
    }

    class CraftViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val imgItem: ImageView = v.findViewById(R.id.imgItemCraft)
        val lblItemName: TextView = v.findViewById(R.id.lblItemNameCraft)
        val lblItemQnty: TextView = v.findViewById(R.id.lblItemQntyCraft)
        val checkCraftable: CheckBox = v.findViewById(R.id.checkIsCrafteable)
    }

    //----------------------------

    class RecipeRecyclerAdapter(val item: Item): RecyclerView.Adapter<RecipeViewHolder>(){
        val recipe = item.recipe
        val keys = recipe.keys.stream().toList()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(R.layout.recipe_list_item, parent, false)
            return RecipeViewHolder(view)
        }

        override fun getItemCount(): Int {
            return recipe.size
        }

        override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
            with(holder){
                lblQntyNeeded.text = "x${recipe[keys[position]]}"
                imgItemRecipe.setImageResource(keys[position].iconResId)
            }
        }

    }

    class RecipeViewHolder(v: View): RecyclerView.ViewHolder(v){
        val lblQntyNeeded: TextView = v.findViewById(R.id.lblQntyNeed)
        val imgItemRecipe: ImageView = v.findViewById(R.id.imgItemRecipe)
    }
}