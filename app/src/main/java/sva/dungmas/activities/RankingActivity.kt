package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sva.dungmas.R
import sva.dungmas.activities.recycler.RecyclerAdapter
import sva.dungmas.enums.Codes

class RankingActivity : AppCompatActivity() {
    private lateinit var recyclerAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        recyclerAdapter = RecyclerAdapter()

        (findViewById<RecyclerView>(R.id.recyclerRankView))
            .adapter = recyclerAdapter
        (findViewById<RecyclerView>(R.id.recyclerRankView))
            .layoutManager = LinearLayoutManager(this)
        setButtonsEvents()
    }

    private fun setButtonsEvents(){
        (findViewById<FloatingActionButton>(R.id.btnClearRanking))
            .setOnClickListener(this::btnClearRankingClick)
    }

    private fun btnClearRankingClick(v: View){
        //Toast.makeText(this, "borrados", Toast.LENGTH_SHORT).show()
    }

    override fun finish() {
        setResult(Codes.OK.code)
        super.finish()
    }
}