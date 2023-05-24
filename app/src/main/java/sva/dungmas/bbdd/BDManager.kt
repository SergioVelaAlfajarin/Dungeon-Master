package sva.dungmas.bbdd

import android.content.Context
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.recyclers.RankingEntry

private const val VERSION = 1

class BDManager(context: Context) {
    private val bdHelper = BDHelper(context)

    fun getCraftableItems(context: Context): List<Item> {
        return listOf(
            Item(1, R.drawable.wood2,   R.string.itemName_wood2,   hashMapOf(getItemsPart(context,1) to 3)),
            Item(2, R.drawable.iron2,   R.string.itemName_iron2,   hashMapOf(getItemsPart(context,2) to 3)),
            Item(3, R.drawable.rock2,   R.string.itemName_rock2,   hashMapOf(getItemsPart(context,3) to 3)),
            Item(4, R.drawable.leaves2, R.string.itemName_leaves2, hashMapOf(getItemsPart(context,4) to 3)),
        )//TODO("Not yet implemented")
    }

    fun getCraftableItems(context: Context, id: Int): Item{
        return getCraftableItems(context)[id - 1]//TODO("Not yet implemented")
    }

    fun getItemsPart(context: Context): List<ItemPart>{
        return listOf(
            ItemPart(1, R.drawable.wood1,   R.string.itemName_wood ),
            ItemPart(2, R.drawable.iron1,   R.string.itemName_iron ),
            ItemPart(3, R.drawable.rock1,   R.string.itemName_rock ),
            ItemPart(4, R.drawable.leaves1, R.string.itemName_leaves),
        )//TODO("Not yet implemented")
    }

    fun getItemsPart(context: Context, id: Int): ItemPart{
        return  getItemsPart(context)[id - 1]//TODO("Not yet implemented")
    }

    fun getRankingPos(context: Context, position: Int): RankingEntry {
        TODO("Not yet implemented")
    }

    fun resetRanking(context: Context) {
        TODO("Not yet implemented")
    }
}