package sva.dungmas.game.entities

import sva.dungmas.game.Game

class Enemy: Entity {
    override var alive = true
    override var vit = vitMax

    override val vitMax: Int
        get(){
            return Game.defaultEnemyStats.vit
        }

    override val atk: Int
        get(){
            return Game.defaultEnemyStats.atk
        }
    override val def: Int
        get(){
            return Game.defaultEnemyStats.def
        }


    override fun attack(other: Entity): Int {
        return other.recieveAttack(atk)
    }

    override fun recieveAttack(atk: Int): Int {
        val dmg = atk - (def / 2)
        vit -= dmg
        if(vit <= 0){
            vit = 0
            alive = false
        }
        return dmg
    }
    override fun toString(): String {
        return "VitMax: $vitMax\nAtk: $atk\nDef: $def"
    }

}