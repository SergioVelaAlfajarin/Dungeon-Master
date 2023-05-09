package sva.dungmas.game.entities

class Armor {
    private val stats = Stats(1,1,1)
    var level = 1

    val icon: Int
        get(){ //TODO iconos amardura
            return when(level){
                in 1..10 -> 0
                in 10..20 -> 1
                else -> 2
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