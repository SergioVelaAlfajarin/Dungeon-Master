package sva.dungmas.recyclers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.game.Game

class RankingRecyclerAdapter(val context: Context): RecyclerView.Adapter<RankingViewHolder>(){
    val ranks = Game.bdManager.getRankings()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rank_list_item, parent, false)
        return RankingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ranks.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.iLblRankPos.text = (position + 1).toString()
        val rank: RankingEntry = ranks[position]
        holder.iLblRankName.text = rank.name
        holder.iLblRankPts.text = rank.pts.toString()
    }
}

class RankingViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val iLblRankPos: TextView = v.findViewById(R.id.lblRankPos)
    val iLblRankName: TextView = v.findViewById(R.id.lblRankName)
    val iLblRankPts: TextView = v.findViewById(R.id.lblRankPts)
}

data class RankingEntry(
    val name: String,
    val pts: Int
)