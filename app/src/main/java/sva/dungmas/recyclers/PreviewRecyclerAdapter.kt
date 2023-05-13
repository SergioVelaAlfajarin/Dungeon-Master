package sva.dungmas.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.Game

class PreviewRecyclerAdapter(val context: Context): RecyclerView.Adapter<PreviewViewHolder>(){
    private val drops = Game.getNextLevelDrop()
    private val keyset = drops.keys.toList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreviewViewHolder {
        val vInflated = LayoutInflater.from(parent.context).inflate(R.layout.preview_dialog_item, parent, false)
        return PreviewViewHolder(vInflated)
    }

    override fun getItemCount(): Int {
        return drops.size
    }

    override fun onBindViewHolder(holder: PreviewViewHolder, position: Int) {
        holder.imgItemIconDialog.setImageResource(keyset[position].iconResId)
        holder.lblItemNameDialog.text = context.getString(keyset[position].nameResId)
        holder.lblItemQntyDialog.text = "x${drops[keyset[position]]}"
    }
}

class PreviewViewHolder(v: View): RecyclerView.ViewHolder(v){
    val imgItemIconDialog: ImageView = v.findViewById(R.id.imgItemIconDialog)
    val lblItemNameDialog: TextView = v.findViewById(R.id.lblItemNameDialog)
    val lblItemQntyDialog: TextView = v.findViewById(R.id.lblItemQntyDialog)
}