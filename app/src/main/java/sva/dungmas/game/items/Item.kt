package sva.dungmas.game.items

class Item(
    override val iconResId: Int,
    override val name: String,
) : Storable {
    val recipe: ArrayList<ItemPart> = arrayListOf()



}