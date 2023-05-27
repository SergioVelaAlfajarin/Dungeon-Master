package sva.dungmas.game.entities

interface Entity {
    var alive: Boolean
    val vitMax: Int
    var vit: Int
    val atk: Int
    val def: Int

    fun attack(other: Entity)
    fun recieveAttack(atk: Int)
}