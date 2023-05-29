package sva.dungmas.game.entities

interface Entity {
    var alive: Boolean
    val vitMax: Int
    var vit: Int
    val atk: Int
    val def: Int

    fun attack(other: Entity): Int
    fun recieveAttack(atk: Int): Int
}