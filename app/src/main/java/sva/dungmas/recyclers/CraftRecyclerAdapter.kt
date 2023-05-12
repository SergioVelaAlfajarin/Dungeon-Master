package sva.dungmas.recyclers

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.activities.CraftActivity
import sva.dungmas.game.Game
import sva.dungmas.game.items.Item

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
        val text = context.getString(R.string.qntyOnInv, Game.player.inventory[item[position]])
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