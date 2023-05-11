package sva.dungmas.game.entities

import sva.dungmas.R
import sva.dungmas.game.Game

class Armor {
    fun increaseLevel() {
        if(Game.easyMode){
            stats.vit += 3
            stats.atk += 3
            stats.def += 3
        }else{
            stats.vit += 2
            stats.atk += 2
            stats.def += 2
        }
        level++
    }

    private val stats = Stats(1,1,1)
    var level = 1
        private set

    val icon: Int
        get(){ //TODO iconos amardura
            return when(level){
                in 1..10 -> R.drawable.iron
                in 10..20 -> R.drawable.wood
                else -> R.drawable.leaves
            }
        }
    var vit: Int
        get(){
            return stats.vit
        }
        set(value){
            stats.vit = value
        }
    var atk: Int
        get(){
            return stats.atk
        }
        set(value){
            stats.atk = value
        }
    var def: Int
        get(){
            return stats.def
        }
        set(value){
            stats.def = value
        }
}