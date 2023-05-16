package sva.dungmas.bbdd

import android.content.Context
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.recyclers.RankingEntry

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
        return listOf(
            Item(1, R.drawable.wood2,   R.string.itemName_wood2, hashMapOf(getItemsPart(1) to 3)),
            Item(2, R.drawable.iron2,   R.string.itemName_iron2, hashMapOf(getItemsPart(2) to 3)),
            Item(3, R.drawable.rock2,   R.string.itemName_rock2, hashMapOf(getItemsPart(3) to 3)),
            Item(4, R.drawable.leaves2, R.string.itemName_leaves2, hashMapOf(getItemsPart(4) to 3)),
        )//TODO("Not yet implemented")
    }

    fun getCraftableItems(id: Int): Item{
        return getCraftableItems()[id - 1]//TODO("Not yet implemented")
    }

    fun getItemsPart(): List<ItemPart>{
        return listOf(
            ItemPart(1, R.drawable.wood1,   R.string.itemName_wood ),
            ItemPart(2, R.drawable.iron1,   R.string.itemName_iron ),
            ItemPart(3, R.drawable.rock1,   R.string.itemName_rock ),
            ItemPart(4, R.drawable.leaves1, R.string.itemName_leaves),
        )//TODO("Not yet implemented")
    }

    fun getItemsPart(id: Int): ItemPart{
        return  getItemsPart()[id - 1]//TODO("Not yet implemented")
    }

    fun getRankingPos(position: Int): RankingEntry {
        TODO("Not yet implemented")
    }

    fun resetRanking() {
        TODO("Not yet implemented")
    }
}