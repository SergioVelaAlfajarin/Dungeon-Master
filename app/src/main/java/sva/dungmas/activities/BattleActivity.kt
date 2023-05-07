package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game

class BattleActivity : AppCompatActivity() {
    private var isRepeating = false
    private var battleWon = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        isRepeating = intent.getBooleanExtra("repeat", false)

        battleWon = true

        onBattleEnd()
    }


    private fun onBattleEnd(){
        if (battleWon && isRepeating) {
            Log.d(":::", "onBattleEnd: battle won and is repeating")
            setResult(Codes.BATTLE_WON.code)
        } else if(battleWon){
            Log.d(":::", "onBattleEnd: battle won and not repeating")
            Game.increaseLevel()
            setResult(Codes.BATTLE_WON.code)
        } else {
            setResult(Codes.BATTLE_LOST.code)
        }
        finish()
    }
}