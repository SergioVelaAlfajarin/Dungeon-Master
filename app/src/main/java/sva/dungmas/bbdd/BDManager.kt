package sva.dungmas.bbdd

import android.content.Context
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart

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
            Item(1, R.drawable.wood, "Wood", hashMapOf()),
            Item(2, R.drawable.iron, "Iron", hashMapOf()),
            Item(3, R.drawable.rock, "Rock", hashMapOf()),
            Item(4, R.drawable.leaves, "Leaves", hashMapOf()),
        )
    }

    fun getCraftableItems(id: Int): Item{
        //TODO("Not yet implemented")
        return listOf(
            Item(1, R.drawable.wood, "Wood", hashMapOf()),
            Item(2, R.drawable.iron, "Iron", hashMapOf()),
            Item(3, R.drawable.rock, "Rock", hashMapOf()),
            Item(4, R.drawable.leaves, "Leaves", hashMapOf()),
        )[id - 1]
    }

    fun getItemsPart(): List<ItemPart>{
        //TODO("Not yet implemented")
        return listOf(
            ItemPart(1, R.drawable.wood, "CWood"),
            ItemPart(2, R.drawable.iron, "CIron"),
            ItemPart(3, R.drawable.rock, "CRock"),
            ItemPart(4, R.drawable.leaves, "CLeaves"),
        )
    }

    fun getItemsPart(id: Int): ItemPart{
        //TODO("Not yet implemented")
        return listOf(
            ItemPart(1, R.drawable.wood, "CWood"),
            ItemPart(2, R.drawable.iron, "CIron"),
            ItemPart(3, R.drawable.rock, "CRock"),
            ItemPart(4, R.drawable.leaves, "CLeaves"),
        )[id - 1]
    }
}