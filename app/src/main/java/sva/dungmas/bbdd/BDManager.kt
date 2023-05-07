package sva.dungmas.bbdd

import android.app.Application
import android.content.ClipData
import android.content.Context
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.Storable
import java.util.ArrayList

class BDManager {
    private var helper: BDHelper? = null

    fun connectTo(context: Context?, name: String, version: Int){
        helper = BDHelper(
            context,
            name,
            null,
            version
        )
    }

    fun disconnet(){
        helper = null
    }

    fun getCraftableItems(): List<Item> {
        //TODO("Not yet implemented")
        return listOf(
            Item(R.drawable.wood, "Wood"),
            Item(R.drawable.iron, "Iron"),
            Item(R.drawable.rock, "Rock"),
        )
    }

    fun getDroppableItems(): ArrayList<Storable> {
        //TODO("Not yet implemented")
        return arrayListOf()
    }
}