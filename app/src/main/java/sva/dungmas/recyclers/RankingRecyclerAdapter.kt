package sva.dungmas.recyclers

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R

class RankingRecyclerAdapter(
    private var ranking: HashMap<Int, String>
): RecyclerView.Adapter<RankingViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.rank_list_item, parent, false)
        return RankingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ranking.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.iLblRankPos.text = "${position + 1}"
        holder.iLblRankName.text = ranking[position]
    }

    fun resetList(){
        ranking = hashMapOf(
            0  to "-",
            1  to "-",
            2  to "-",
            3  to "-",
            4  to "-",
            5  to "-",
            6  to "-",
            7  to "-",
            8  to "-",
            9 to "-",
        )

        notifyDataSetChanged()
    }
}

class RankingViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val iLblRankPos: TextView = v.findViewById(R.id.lblRankPos)
    val iLblRankName: TextView = v.findViewById(R.id.lblRankName)
}