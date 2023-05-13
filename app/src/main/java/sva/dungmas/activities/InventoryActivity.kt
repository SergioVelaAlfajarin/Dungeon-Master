package sva.dungmas.activities

import InventoryRecyclerAdapter
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import sva.dungmas.game.entities.Inventory
import kotlin.streams.toList

class InventoryActivity : AppCompatActivity() { //TODO TEST
    private lateinit var playerInv: Inventory
    private lateinit var recyclerAdapter: InventoryRecyclerAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var lblEmptyInv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)

        (findViewById<TextView>(R.id.lblInvTitle))
            .text = getString(R.string.invTitle, Game.player.name)

        lblEmptyInv = findViewById(R.id.lblEmptyInv)
        playerInv = Game.player.inventory

        if(playerInv.isEmpty){
            lblEmptyInv.visibility = View.VISIBLE
            return
        }

        recyclerAdapter = InventoryRecyclerAdapter(this, playerInv)
        recyclerView = findViewById(R.id.recyclerInvView)

        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        registerForContextMenu(recyclerView)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            Codes.CONTEXT_DEL.code -> delItem(item.groupId)
        }
        return true
    }

    private fun delItem(groupId: Int) {
        recyclerAdapter.del(groupId)
        recyclerAdapter.updateInventory()
    }
}