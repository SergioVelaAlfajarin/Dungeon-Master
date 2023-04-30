package sva.dungmas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import sva.dungmas.R
import sva.dungmas.dialogs.DialogCreator
import sva.dungmas.game.DefaultJobs

class CreatePlayerActivity : AppCompatActivity() {
    private lateinit var spinner: Spinner
    private lateinit var etName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_player)

        spinner = findViewById(R.id.spinPlayerJob)
        etName = findViewById(R.id.etPlayerName)

        fillSpinner()
        setButtonsEvents()
    }

    private fun fillSpinner(){
        val jobs = DefaultJobs.values()
        val jobsName = jobs.map { getJobName(it) }

        spinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            jobsName
        )
    }

    private fun getJobName(job: DefaultJobs): String{
        return when(job){
            DefaultJobs.KNIGHT -> getString(R.string.knightJobName)
            DefaultJobs.ASSASSIN -> getString(R.string.assassinJobName)
            DefaultJobs.TANK -> getString(R.string.tankJobName)
        }
    }

    private fun setButtonsEvents(){
        (findViewById<Button>(R.id.btnCreatePlayer))
            .setOnClickListener(this::btnCreatePlayerClick)
    }

    private fun btnCreatePlayerClick(v: View){
        val spinnerText: String = spinner.selectedItem.toString()
        val etText: String = etName.text.toString()

        if(etText.isEmpty()){
            DialogCreator.createDialog("et vacio", this)
        }

        Toast.makeText(
            this,
            "spinner: $spinnerText, et: $etText",
            Toast.LENGTH_SHORT
        ).show()
    }
}