package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sva.dungmas.R
import sva.dungmas.dialogs.ConfirmCallback
import sva.dungmas.dialogs.ConfirmDialog
import sva.dungmas.enums.Codes

class RankingActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: RankingRecyclerAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        val list = hashMapOf(
            0  to "YOOOO",
            1  to "-",
            2  to "-",
            3  to "-",
            4  to "-",
            5  to "-",
            6  to "-",
            7  to "-",
            8  to "-",
            9  to "-",
        )

        recyclerAdapter = RankingRecyclerAdapter(list)
        recyclerView = findViewById(R.id.recyclerRankView)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        setButtonsEvents()
    }

    private fun setButtonsEvents(){
        (findViewById<FloatingActionButton>(R.id.btnClearRanking))
            .setOnClickListener(this::btnClearRankingClick)
    }

    private fun btnClearRankingClick(v: View) =
        ConfirmDialog(
            getString(R.string.clearRankingTitle),
            getString(R.string.clearRankingMsg),
            getString(R.string.cancelOp),
            getString(R.string.acceptOp),
            object : ConfirmCallback {
                override fun dialogOk() {
                    //TODO delete also on database
                    recyclerAdapter.resetList()
                }
            }
        )

    override fun finish() {
        setResult(Codes.OK.code)
        super.finish()
    }

    //-----------------------------

    class RankingRecyclerAdapter(
        var ranking: HashMap<Int, String>
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
}