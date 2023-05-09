package sva.dungmas.game.items

import sva.dungmas.R

data class Item(
    override val iconResId: Int,
    override val name: String,
    val recipe: HashMap<Storable, Int> = hashMapOf(
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.iron, "iron") to 2,
        ItemPart(R.drawable.leaves, "leaves") to 2
    )
) : Storable