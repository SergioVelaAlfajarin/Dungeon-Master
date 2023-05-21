package sva.dungmas.game

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.core.content.edit
import sva.dungmas.R
import sva.dungmas.bbdd.BDManager
import sva.dungmas.game.entities.Enemy
import sva.dungmas.game.entities.Player
import sva.dungmas.game.entities.Stats
import sva.dungmas.game.items.Item
import sva.dungmas.game.items.ItemPart
import sva.dungmas.game.items.Storable
import kotlin.math.min

object Game {
    lateinit var player: Player
    lateinit var bdManager: BDManager
    lateinit var enemies: ArrayList<Enemy>
    lateinit var defaultEnemyStats: Stats
    private lateinit var preferences: SharedPreferences

    fun init(preferences: SharedPreferences){
        this.preferences = preferences
        reset()
    }

    fun reset() {
        defaultEnemyStats = Stats(10,1,1)
        bdManager = BDManager()
        genEnemies()
        level = 0
    }

    fun increaseLevel(){
        level++
        increaseDefaultStats()
        genEnemies()
    }

    private fun increaseDefaultStats(){
        defaultEnemyStats.vit += (level * 1.5).toInt()
        defaultEnemyStats.atk += (level * 1.5).toInt()
        defaultEnemyStats.def += (level * 1.5).toInt()
    }

    private fun genEnemies() {
        Log.d(":::", "genEnemies: Generating lvl $level enemies.")
        enemies = arrayListOf()
        for (i in 1..3){
            enemies.add(Enemy(i, "Enemy"))
        }
    }

    fun getLevelDrop(context: Context): LinkedHashMap<ItemPart, Int> {
        val items: List<ItemPart> = bdManager.getItemsPart(context)
        val hashMap: LinkedHashMap<ItemPart, Int> = linkedMapOf()
        val qnty = min((level.coerceAtLeast(1) * (if(!easyMode) 1.5 else 2.5)).toInt(), 99)
        items.forEach{
            hashMap[it] = qnty
        }
        Log.d(":::", "getLevelDrop: $hashMap, level: $level")
        return hashMap
    }

    fun getNextLevelDrop(context: Context): LinkedHashMap<ItemPart, Int> {
        level++
        val hashmap = getLevelDrop(context)
        level--
        return hashmap
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