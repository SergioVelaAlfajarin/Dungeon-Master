package sva.dungmas.game.items

import sva.dungmas.R

class Item(
    override val iconResId: Int,
    override val name: String,
) : Storable {
    val recipe: LinkedHashMap<ItemPart, Int> = linkedMapOf(
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.leaves, "leaves") to 2
    )



}