package sva.dungmas.game.entities

import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.items.Storable

class Armor {
    fun increaseLevel() {
        stats.vit += if(Game.easyMode){
            120 - (Game.level * 2).coerceAtMost(30)
        } else{
            70 - (Game.level * 2).coerceAtMost(20)
        }
        stats.atk += if(Game.easyMode){
            30 - (Game.level / 2).coerceAtMost(5)
        } else{
            20 - (Game.level / 2).coerceAtMost(10)
        }
        stats.def += if(Game.easyMode){
            30 - (Game.level / 2).coerceAtMost(5)
        } else{
            20 - (Game.level / 2).coerceAtMost(10)
        }
        level++
    }

    fun canBeUpgraded():Boolean{
        var upgradable = true
        getRequirementsForNextLevel().forEach{(key, value) ->
            val enough = Game.player.inventory.hasEnoughOf(key, value)
            if(!enough){
                upgradable = false
            }
        }
        return upgradable
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

    override fun toString(): String {
        return stats.toString()
    }

    private val stats = Stats(10,1,1)

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