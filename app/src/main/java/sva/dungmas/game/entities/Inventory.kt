package sva.dungmas.game.entities

import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.game.items.Storable


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

    operator fun get(it: Storable): Int {
        return inventory[it] ?: 0
    }

    fun removeAll(storable: Storable){
        inventory.remove(storable)
    }

    fun removeOne(storable: Storable){
        if(inventory.containsKey(storable)){
            val currrent = inventory[storable]?.minus(1) ?: 0
            if(currrent > 0){
                inventory[storable] = currrent
            }else{
                inventory.remove(storable)
            }
        }
    }

    fun remove(storable: Storable, howMany: Int){
        if(inventory.containsKey(storable)){
            val currrent = inventory[storable]?.minus(howMany) ?: 0
            if(currrent > 0){
                inventory[storable] = currrent
            }else{
                inventory.remove(storable)
            }
        }
    }

    fun hasEnoughOf(storable: Storable, howMany: Int): Boolean{
        val got = get(storable)
        return got >= howMany
    }

    fun checkIfItemIsCraftable(item: Item): Boolean {
        var isCraftable = true
        item.recipe.forEach { (key, value) -> 
            if(!hasEnoughOf(key, value)){
                isCraftable = false
            }
        }
        return isCraftable
    }

    fun add(it: Storable) {
        add(it, 1)
    }

    fun add(it: Storable, qnty: Int){
        //Log.d(":::", "add: it: $it, qnty: $qnty")
        inventory[it] = (inventory[it] ?: 0) + qnty
    }

    fun addItemsDropped(itemsDropped: LinkedHashMap<ItemPart, Int>) {
        itemsDropped.forEach { (key, value) ->
            add(key, value)
        }
    }
}