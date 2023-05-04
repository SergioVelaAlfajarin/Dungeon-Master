package sva.dungmas.game.entities

import sva.dungmas.game.Game

class Enemy(
    val name: String,
): Entity {
    override var alive = true

    override val vit: Int
        get(){
            return Game.defaultEnemyStats.vit
        }
    override val atk: Int
        get(){
            return Game.defaultEnemyStats.vit
        }
    override val def: Int
        get(){
            return Game.defaultEnemyStats.vit
        }


    override fun attack(other: Entity) {
        TODO("Not yet implemented")
    }
}