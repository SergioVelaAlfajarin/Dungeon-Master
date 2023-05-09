package sva.dungmas.game.entities

import android.util.Log
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.Storable
import kotlin.streams.toList


class Inventory {
    private val inventory: LinkedHashMap<Storable, Int> = linkedMapOf()

    val isEmpty: Boolean
        get(){
            return inventory.size == 0
        }
    val size: Int
        get(){
            return inventory.size
        }
    val keySet: MutableSet<Storable>
        get(){
            return inventory.keys
        }

    fun add(it: Storable) {
        inventory[it] = (inventory[it] ?: 0) + 1
    }

    operator fun get(it: Storable): Int {
        return inventory[it] ?: 0
    }

    fun checkIfItemIsCraftable(item: Item): Boolean {
        //TODO tests
        var isCraftable = true
        item.recipe.forEach { (key, value) -> 
            val qntyInInv = inventory[key] ?: 0
            if(qntyInInv < value){
                isCraftable = false
            }
        }
        return isCraftable
    }


}