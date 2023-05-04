package sva.dungmas.game.entities

import sva.dungmas.enums.DefaultJobs

class Player(
    val name: String,
    val job: DefaultJobs //estas estadisticas seran las que iran cambiando
): Entity {
    val inventory = Inventory()
    val armor = Armor()
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


    override fun attack(other: Entity) {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return "Player(name='$name', job=$job, inventory=$inventory, armor=$armor)"
    }
}