package sva.dungmas.game

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import sva.dungmas.bbdd.BDManager
import sva.dungmas.game.entities.Enemy
import sva.dungmas.game.entities.Player
import sva.dungmas.game.entities.Stats
import sva.dungmas.game.items.ItemPart
import kotlin.math.min

object Game {
    private var points = 0
    lateinit var player: Player
    lateinit var bdManager: BDManager
    lateinit var enemy: Enemy
    lateinit var defaultEnemyStats: Stats
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        this.preferences = context.getSharedPreferences("gamePrefs", Context.MODE_PRIVATE)
        bdManager = BDManager(context)
        reset()
    }

    fun reset() {
        defaultEnemyStats = Stats(20,2,2)
        enemy = Enemy()
        level = 0
        points = 0
    }

    fun increaseLevel(){
        level++
        increaseDefaultStats()
        enemy = Enemy()
    }

    fun addPoints(pts: Int){
        points += pts
    }

    private fun increaseDefaultStats(){
        defaultEnemyStats.vit += (level * 1.5).toInt()
        if(defaultEnemyStats.vit > 999) defaultEnemyStats.vit = 999
        defaultEnemyStats.atk += (level * 1.1).toInt()
        if(defaultEnemyStats.atk > 99) defaultEnemyStats.atk = 99
        defaultEnemyStats.def += (level * 1.1).toInt()
        if(defaultEnemyStats.def > 99) defaultEnemyStats.def = 99
    }

    fun getLevelDrop(): LinkedHashMap<ItemPart, Int> {
        val items: List<ItemPart> = bdManager.getItemsPart()
        val hashMap: LinkedHashMap<ItemPart, Int> = linkedMapOf()
        val qntity = min((level.coerceAtLeast(1) * (if(!easyMode) 1.6 else 2.4)).toInt(), 99)
        items.forEach{
            hashMap[it] = qntity
        }
        Log.d(":::", "getLevelDrop: $hashMap, level: $level")
        return hashMap
    }

    fun getNextLevelDrop(): LinkedHashMap<ItemPart, Int> {
        level++
        val hashmap = getLevelDrop()
        level--
        return hashmap
    }

    fun saveRanking() {
        bdManager.saveRank(player, points)
    }

    var easyMode: Boolean
        get(){
            return preferences.getBoolean("easyMode", false)
        }
        set(value){
            preferences.edit {
                putBoolean("easyMode", value)
            }
        }
    var darkMode: Boolean
        get(){
            return preferences.getBoolean("darkMode", false)
        }
        set(value){
            preferences.edit {
                putBoolean("darkMode", value)
            }
        }
    var lang: String
        get(){
            return preferences.getString("lang", "") ?: ""
        }
        set(value){
            preferences.edit {
                putString("lang", value)
            }
        }
    var level = 0
        private set
}