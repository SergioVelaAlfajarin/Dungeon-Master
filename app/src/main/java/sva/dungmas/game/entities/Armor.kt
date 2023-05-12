package sva.dungmas.game.entities

import sva.dungmas.R
import sva.dungmas.bbdd.BDManager
import sva.dungmas.game.Game
import sva.dungmas.game.items.Storable

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

    fun getRequirementsForNextLevel(): LinkedHashMap<Storable, Int>{
        val qnty = (level * (if(Game.easyMode) 1.5 else 2.5)).toInt()
        return linkedMapOf(
            Game.bdManager.getCraftableItems(1) to qnty,
            Game.bdManager.getCraftableItems(2) to qnty,
            Game.bdManager.getCraftableItems(3) to qnty,
            Game.bdManager.getCraftableItems(4) to qnty,
        )
    }

    private val stats = Stats(1,1,1)
    var level = 1
        private set

    val icon: Int
        get(){
            return when(level){
                in 1 until 10 -> R.drawable.armor1
                in 10 until 20 -> R.drawable.armor2
                else -> R.drawable.armor3
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