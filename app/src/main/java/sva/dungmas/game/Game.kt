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

object Game {
    lateinit var player: Player
    lateinit var bdManager: BDManager
    lateinit var enemies: ArrayList<Enemy>
    lateinit var defaultEnemyStats: Stats
    private lateinit var preferences: SharedPreferences

    fun init(preferences: SharedPreferences){
        this.preferences = preferences
        defaultEnemyStats = Stats(1,1,1)
        bdManager = BDManager()
        genEnemies()
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

    fun getLevelDrop(): HashMap<Storable, Int> { //always be itempart
        val items: ArrayList<Storable> = bdManager.getDroppableItems()
        val hashMap: HashMap<Storable, Int> = hashMapOf()
        val qnty = (level * (if(easyMode) 1.5 else 3.0)).toInt()
        items.forEach{
            hashMap[it] = qnty
        }
        Log.d(":::", "getLevelDrop: $hashMap")
        return hashMap
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
    var level = 1
        private set
}