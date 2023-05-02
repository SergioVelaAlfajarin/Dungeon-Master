package sva.dungmas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import sva.dungmas.R
import sva.dungmas.dialogs.DialogCreator
import sva.dungmas.enums.Codes
import sva.dungmas.enums.DefaultJobs
import sva.dungmas.game.Game
import sva.dungmas.game.entities.Player

class CreatePlayerActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var etName: EditText
    private lateinit var lblJobInfo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_player)

        spinner = findViewById(R.id.spinPlayerJob)
        etName = findViewById(R.id.etPlayerName)
        lblJobInfo = findViewById(R.id.lblJobInfo)

        fillSpinner()
        setButtonsEvents()
    }

    private fun fillSpinner(){
        val jobs = DefaultJobs.values()
        val jobsName = jobs.map { getNameByJob(it) }

        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            jobsName
        )

        spinner.onItemSelectedListener =  object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val job = getJobByName(spinner.adapter.getItem(id.toInt()).toString())
                updateLabel(job)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                lblJobInfo.text = ""
            }
        }
    }

    private fun updateLabel(job: DefaultJobs) {
        lblJobInfo.text = """
            ${getString(R.string.vit, job.stats.vit)}
            ${getString(R.string.atk, job.stats.atk)}
            ${getString(R.string.def, job.stats.def)}
        """.trimIndent()
    }

    private fun getNameByJob(job: DefaultJobs): String{
        return when(job){
            DefaultJobs.KNIGHT -> getString(R.string.knightJobName)
            DefaultJobs.ASSASSIN -> getString(R.string.assassinJobName)
            DefaultJobs.TANK -> getString(R.string.tankJobName)
        }
    }

    private fun getJobByName(name: String): DefaultJobs{
        return when(name){
            getString(R.string.knightJobName) -> DefaultJobs.KNIGHT
            getString(R.string.assassinJobName) -> DefaultJobs.ASSASSIN
            else -> DefaultJobs.TANK //evito errores
        }
    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnCreatePlayer))
            .setOnClickListener(this::btnCreatePlayerClick)
    }

    private fun btnCreatePlayerClick(v: View){
        val job = getJobByName(spinner.selectedItem.toString())
        val name = etName.text.toString()

        if(name.isEmpty()){
            DialogCreator.createSimpleDialog("et vacio", this)
            return
        }

        Game.player = Player(name, job)

        Log.d(":::", "btnCreatePlayerClick: " + Game.player.toString())

        val it = Intent(this, RestZoneActivity::class.java)
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(it)
        setResult(Codes.OK.code)
        finish()
    }
}