package sva.dungmas.game.entities

import sva.dungmas.game.Game

class Enemy(
    val id: Int,
    val name: String,
): Entity {
    override var alive = true
    override var vit = vitMax

    override val vitMax: Int
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
        other.recieveAttack(atk)
    }

    override fun recieveAttack(atk: Int) {
        val dmg = atk - (def / 2)
        vit -= dmg
        if(vit <= 0){
            vit = 0
            alive = false
        }
    }
}