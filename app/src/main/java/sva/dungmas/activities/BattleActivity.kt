package sva.dungmas.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import sva.dungmas.recyclers.BattleRecyclerAdapter

class BattleActivity : AppCompatActivity() {
    private var isRepeating = false
    private var battleWon = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        isRepeating = intent.getBooleanExtra("repeat", false)

        val logs  = runBattle()
        val recycler = findViewById<RecyclerView>(R.id.recyclerBattle)
        recycler.adapter = BattleRecyclerAdapter(logs)
        recycler.layoutManager = LinearLayoutManager(this)

        val btn = findViewById<Button>(R.id.btnBattleContinue)
        btn.setOnClickListener(this::onBattleEnd)
        btn.isEnabled = true
    }

    /**
     * Runs battle, fills logs and updates the battleWon variable
     */
    private fun runBattle(): ArrayList<String> {
        battleWon = false
        return arrayListOf(
            "teeesting",
            "teeesting",
            "teeesting",
            "teeesting"
        )
    }

    private fun onBattleEnd(v: View) {
        if (battleWon && isRepeating) {
            Log.d(":::", "onBattleEnd: battle won and is repeating")
            setResult(Codes.BATTLE_WON.code)
        } else if (battleWon) {
            Log.d(":::", "onBattleEnd: battle won and not repeating")
            Game.increaseLevel()
            setResult(Codes.BATTLE_WON.code)
        } else {
            setResult(Codes.BATTLE_LOST.code)
        }
        finish()
    }
}