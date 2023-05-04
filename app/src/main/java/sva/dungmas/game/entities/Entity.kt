package sva.dungmas.game.entities

interface Entity {
    var alive: Boolean
    val vit: Int
    val atk: Int
    val def: Int

    fun attack(other: Entity)
}