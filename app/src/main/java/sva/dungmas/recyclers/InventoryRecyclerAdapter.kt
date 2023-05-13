import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.entities.Inventory
import kotlin.streams.toList

class InventoryRecyclerAdapter(
    private val context: Context,
    private val inv: Inventory
): RecyclerView.Adapter<InventoryViewHolder>(){
    private var keyset = inv.keySet.stream().toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.inv_list_item, parent, false)
        return InventoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return inv.size
    }

    override fun onBindViewHolder(holder: InventoryViewHolder, position: Int) {
        holder.imgItem.setImageResource(keyset[position].iconResId)
        holder.lblItemName.text = context.getString(keyset[position].nameResId)
        holder.lblItemQnty.text = context.getString(R.string.qntyOnInv, inv[keyset[position]])
    }

    fun updateInventory(){
        keyset = inv.keySet.stream().toList()
        notifyDataSetChanged()
    }
}

class InventoryViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val imgItem: ImageView = v.findViewById(R.id.imgItemInv)
    val lblItemName: TextView = v.findViewById(R.id.lblItemNameInv)
    val lblItemQnty: TextView = v.findViewById(R.id.lblItemQntyInv)
}