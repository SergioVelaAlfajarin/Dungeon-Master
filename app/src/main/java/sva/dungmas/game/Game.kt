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
    lateinit var enemies: ArrayList<Enemy>
    lateinit var defaultEnemyStats: Stats
    private lateinit var preferences: SharedPreferences

    fun init(context: Context){
        this.preferences = context.getSharedPreferences("gamePrefs", Context.MODE_PRIVATE)
        bdManager = BDManager(context)
        reset()
    }

    fun reset() {
        defaultEnemyStats = Stats(40,2,2)
        genEnemies()
        level = 0
        points = 0
    }

    fun increaseLevel(){
        level++
        increaseDefaultStats()
        genEnemies()
    }

    fun addPoints(pts: Int){
        points += pts
    }

    private fun increaseDefaultStats(){
        defaultEnemyStats.vit += (level * 1.5).toInt()
        defaultEnemyStats.atk += (level * 1.1).toInt()
        defaultEnemyStats.def += (level * 1.1).toInt()
    }

    private fun genEnemies() {
        Log.d(":::", "genEnemies: Generating lvl $level enemies.")
        enemies = arrayListOf()
        for (i in 1..3){
            enemies.add(Enemy(i, "Enemy"))
        }
    }

    fun getLevelDrop(): LinkedHashMap<ItemPart, Int> {
        val items: List<ItemPart> = bdManager.getItemsPart()
        val hashMap: LinkedHashMap<ItemPart, Int> = linkedMapOf()
        val qntity = min((level.coerceAtLeast(1) * (if(!easyMode) 1.5 else 2.5)).toInt(), 99)
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
        Log.d(":::", "saveRanking: ${this.points}")
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