package sva.dungmas.game.items

data class Item(
    val id: Int,
    override val iconResId: Int,
    override val name: String,
    val recipe: HashMap<Storable, Int>
) : Storable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Item) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}