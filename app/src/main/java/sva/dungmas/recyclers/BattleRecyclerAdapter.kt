package sva.dungmas.recyclers

import android.graphics.Color
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R

class BattleRecyclerAdapter(private val logs: ArrayList<String>): RecyclerView.Adapter<BattleViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BattleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.battle_list_item, parent, false)
        return BattleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return logs.size
    }

    override fun onBindViewHolder(holder: BattleViewHolder, position: Int) {
        //TODO MEJORAR LOS MENSAJES DE LOG
        val next = position + 1
        if(next == itemCount){
            holder.lbl.setTextColor(Color.RED)
            holder.lbl.setTypeface(null, Typeface.BOLD)
            holder.lbl.text = logs[position]
        }else{
            holder.lbl.text = "${next}.- ${logs[position]}"
        }
    }
}

class BattleViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val lbl: TextView = v.findViewById(R.id.lblMsgBattle)
}