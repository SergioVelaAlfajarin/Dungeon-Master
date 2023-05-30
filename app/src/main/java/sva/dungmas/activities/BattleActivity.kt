package sva.dungmas.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sva.dungmas.R
import sva.dungmas.enums.Codes
import sva.dungmas.game.Game
import sva.dungmas.recyclers.BattleRecyclerAdapter

class BattleActivity : AppCompatActivity() {
    private var isRepeating = false
    private var battleWon = true
    private lateinit var logs: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_battle)

        isRepeating = intent.getBooleanExtra("repeat", false)

        logs = runBattle()

        val recycler = findViewById<RecyclerView>(R.id.recyclerBattle)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = BattleRecyclerAdapter(logs)

        val btnContinue = findViewById<Button>(R.id.btnBattleContinue)
        btnContinue.setOnClickListener(this::onBattleEnd)
        btnContinue.isEnabled = true


        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBattleEnd(null)
            }
        })
    }

    /**
     * returns true if player and enemies are still alive.
     * false otherwise.
     */
    private fun repeatBattle(): Boolean {
        return Game.player.alive && Game.enemy.alive
    }

    /**
     * Runs battle, fills logs and updates the battleWon variable
     */
    private fun runBattle(): ArrayList<String> {
        /*
        val executor = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

        executor.execute{
            while(repeatBattle()){
                Thread.sleep(1000)
                var dmgDone = Game.player.attack(Game.enemy)
                addItem("player did $dmgDone")

                Thread.sleep(1000)
                dmgDone = Game.enemy.attack(Game.player)
                addItem("enemy did $dmgDone")
            }

            handler.post{
               // btnContinue.isEnabled = true
            }
        }
        */

        return arrayListOf(
            "teeesting",
            "teeesting",
            "teeesting",
            "teeesting"
        )
    }

    private fun onBattleEnd(v: View?) {
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