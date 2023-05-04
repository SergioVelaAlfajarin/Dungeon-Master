package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game

class BattleActivity : AppCompatActivity() {
    private var isRepeating = false
    private var battleWon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)
        isRepeating = savedInstanceState?.getBoolean("repeat") ?: false


    }


    fun onBattleEnd(){
        if (battleWon && isRepeating) {
            setResult(Codes.BATTLE_WON.code)
        } else if(battleWon){
            Game.increaseLevel()
            setResult(Codes.BATTLE_WON.code)
        } else {
            setResult(Codes.BATTLE_LOST.code)
        }
        finish()
    }
}