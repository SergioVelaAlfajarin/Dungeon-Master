package sva.dungmas.game.entities

import sva.dungmas.R
import sva.dungmas.game.Game
import sva.dungmas.game.items.Storable

class Armor {
    fun increaseLevel() {
        stats.vit += vitUpgrade
        stats.atk += atkUpgrade
        stats.def += defUpgrade
        level++
        //Game.bdManager.updatePlayerStats(Game.player)
    }

    val vitUpgrade: Int
        get(){
            return if(Game.easyMode){
                999.coerceAtMost(120 - (level * 2).coerceAtMost(30))
            } else{
                999.coerceAtMost(70 - (level * 2).coerceAtMost(20))
            }
        }
    val atkUpgrade: Int
        get(){
            return if(Game.easyMode){
                99.coerceAtMost(30 - (level / 2).coerceAtMost(5))
            } else{
                99.coerceAtMost(20 - (level / 2).coerceAtMost(10))
            }
        }
    val defUpgrade: Int
        get(){
            return if(Game.easyMode){
                99.coerceAtMost(30 - (level / 2).coerceAtMost(5))
            } else{
                99.coerceAtMost(20 - (level / 2).coerceAtMost(10))
            }
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
        val qnty = (level * (if(Game.easyMode) 1.1 else 2.0)).toInt()
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