package sva.dungmas.game.entities

import android.util.Log
import sva.dungmas.R
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.game.items.Storable
import kotlin.streams.toList


class Inventory {
    //TODO tests
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

    operator fun get(it: Storable): Int {
        return inventory[it] ?: 0
    }

    fun checkIfItemIsCraftable(item: Item): Boolean {
        var isCraftable = true
        item.recipe.forEach { (key, value) -> 
            val qntyInInv = inventory[key] ?: 0
            if(qntyInInv < value){
                isCraftable = false
            }
        }
        return isCraftable
    }

    fun add(it: Storable) {
        add(it, 1)
    }

    fun add(it: Storable, qnty: Int){
        Log.d(":::", "add: it: $it, qnty: $qnty")
        inventory[it] = (inventory[it] ?: 0) + qnty
    }

    fun addItemsDropped(itemsDropped: LinkedHashMap<ItemPart, Int>) {
        itemsDropped.forEach { (key, value) ->
            add(key, value)
        }
    }
}