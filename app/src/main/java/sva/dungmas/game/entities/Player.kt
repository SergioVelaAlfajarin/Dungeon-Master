package sva.dungmas.game.entities

import sva.dungmas.enums.DefaultJobs

class Player(
    val name: String,
    val job: DefaultJobs //estas estadisticas seran las que iran cambiando
): Entity {
    val inventory = Inventory()
    val armor = Armor()



    override fun toString(): String {
        return "Player(name='$name', job=$job, inventory=$inventory, armor=$armor)"
    }
}