package sva.dungmas.activities.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R

class RecyclerAdapter: RecyclerView.Adapter<ViewHolder>() {
    private val list = listOf("placeholder1","placeholder2","placeholder3")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val string = list[position]
    }
}