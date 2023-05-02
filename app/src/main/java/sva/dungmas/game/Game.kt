package sva.dungmas.game

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import sva.dungmas.bbdd.BDManager
import sva.dungmas.game.entities.Player

object Game {
    lateinit var player: Player
    lateinit var preferences: SharedPreferences
    lateinit var bdManager: BDManager
    var level = 1

    fun init(appContext: Context){
        preferences = appContext.getSharedPreferences("gamePrefs", Context.MODE_PRIVATE)
        bdManager = BDManager()
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
}