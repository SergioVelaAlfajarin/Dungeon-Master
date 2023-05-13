package sva.dungmas.recyclers

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.Storable
import kotlin.streams.toList

class RecipeRecyclerAdapter(private val recipe: HashMap<Storable, Int>): RecyclerView.Adapter<RecipeViewHolder>(){
    private val inv = Game.player.inventory
    private val keys = recipe.keys.stream().toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recipe_list_item, parent, false)
        return RecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return keys.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val qntyNeeded = recipe[keys[position]] ?: 0
        val icon = keys[position].iconResId

        with(holder){
            lblQntyNeeded.text = "x${qntyNeeded}"
            imgItemRecipe.setImageResource(icon)

            if(!inv.hasEnoughOf(keys[position], qntyNeeded)){
                itemView.setBackgroundColor(Color.red(100))
            }else{
                itemView.setBackgroundColor(Color.TRANSPARENT)
            }
        }
    }

}

class RecipeViewHolder(v: View): RecyclerView.ViewHolder(v){
    val lblQntyNeeded: TextView = v.findViewById(R.id.lblQntyNeed)
    val imgItemRecipe: ImageView = v.findViewById(R.id.imgItemRecipe)
}