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

    override fun toString(): String {
        return "VitMax: $vitMax\nAtk: $atk\nDef: $def"
    }

}