package sva.dungmas.game.entities

import sva.dungmas.enums.DefaultJobs

class Player(
    val id: Int,
    val name: String,
    private val job: DefaultJobs
): Entity {
    val inventory = Inventory()
    val armor = Armor()
    override var alive = true
    override var vit = vitMax

    override fun toString(): String {
        return "Vit: $vit/$vitMax\nAtk: $atk\nDef: $def"
    }

    override val vitMax: Int
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

    fun updateCurrentVit(){
        vit = vitMax
    }
}