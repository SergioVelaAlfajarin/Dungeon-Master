package sva.dungmas.game.entities

import sva.dungmas.enums.DefaultJobs
import sva.dungmas.game.items.ItemPart
import sva.dungmas.game.items.Storable

class Player(
    val name: String,
    val job: DefaultJobs
): Entity {
    val inventory = Inventory()
    val armor = Armor() //estas estadisticas seran las que iran cambiando. las default no
    override var alive = true

    override val vit: Int
        get(){
            return job.stats.vit + armor.vit
        }
    override val atk: Int
        get(){
            return job.stats.atk + armor.atk
        }
    override val def: Int
        get(){
            return job.stats.def + armor.def
        }

    fun addItemsToInventory(ip: ArrayList<Storable>){
        ip.forEach {
            inventory.add(it)
        }
    }

    override fun attack(other: Entity) {
        TODO("Not yet implemented")
    }
}