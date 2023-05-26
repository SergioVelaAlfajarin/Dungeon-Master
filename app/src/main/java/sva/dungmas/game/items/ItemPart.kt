package sva.dungmas.game.items

import sva.dungmas.R

data class ItemPart(
    val id: Int,
    override val nameResId: Int,
    override val iconResId: Int,
) : Storable{
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ItemPart) return false
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}