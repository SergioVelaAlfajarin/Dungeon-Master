package sva.dungmas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import sva.dungmas.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setButtonsEvents()
    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnStartGame))
           .setOnClickListener(this::btnStartGameClick)
        (findViewById<Button>(R.id.btnRanking))
            .setOnClickListener(this::btnRankingClick)
        (findViewById<Button>(R.id.btnSettings))
            .setOnClickListener(this::btnSettingsClick)
    }

    private fun btnStartGameClick(v: View){
        val it = Intent(this, CreatePlayerActivity::class.java)
        startActivity(it)
    }

    private fun btnRankingClick(v: View){
        val it = Intent(this, RankingActivity::class.java)
        startActivity(it)
    }

    private fun btnSettingsClick(v: View){
        val it = Intent(this, SettingsActivity::class.java)
        startActivity(it)
    }
}