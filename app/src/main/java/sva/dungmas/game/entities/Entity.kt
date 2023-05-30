package sva.dungmas.game.entities

import android.util.Log

interface Entity {
    var alive: Boolean
    val vitMax: Int
    var vit: Int
    val atk: Int
    val def: Int

    fun attack(other: Entity): Int{
        return other.receiveAttack(atk * 2)
    }
    fun receiveAttack(atk: Int): Int{
        val reduction = (atk - (def / 10)).coerceAtLeast(0)
        Log.d(":::", "receiveAttack: dmg: $atk, reduction: $reduction")
        vit -= reduction
        if(vit <= 0){
            vit = 0
            alive = false
        }
        return reduction
    }
}