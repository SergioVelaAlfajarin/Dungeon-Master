package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import sva.dungmas.R
import sva.dungmas.enums.Codes

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        setButtonsEvents()
    }

    private fun setButtonsEvents(){
        (findViewById<FloatingActionButton>(R.id.btnClearRanking))
            .setOnClickListener(this::btnClearRankingClick)
    }

    private fun btnClearRankingClick(v: View){
        //Toast.makeText(this, "borrados", Toast.LENGTH_SHORT).show()
    }

    override fun finish() {
        setResult(Codes.OK.code)
        super.finish()
    }
}