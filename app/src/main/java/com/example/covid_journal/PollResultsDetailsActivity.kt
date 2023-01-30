package com.example.covid_journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference

class PollResultsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll_results_details)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val currentUserID = auth.currentUser?.uid.toString()
        var specificDayResults = 1
        database.child(currentUserID).get().addOnSuccessListener {
            specificDayResults = it.child("specificDayResults").value.toString().toInt()
        }.addOnFailureListener {
            specificDayResults = 1
        }

        val goBackButton = findViewById<Button>(R.id.goBackFromPollResultsDetailsButton)

        val difficultyBreathingSymptom = findViewById<TextView>(R.id.difficultyBreathingSymptom)
        val mobilitySymptom = findViewById<TextView>(R.id.mobilitySymptom)
        val chestPainSymptom = findViewById<TextView>(R.id.chestPainSymptom)
        val lossOfSenseSymptom = findViewById<TextView>(R.id.lossOfSenseSymptom)
        val temperatureSymptom = findViewById<TextView>(R.id.temperatureSymptom)
        val coughSymptom = findViewById<TextView>(R.id.coughSymptom)
        val headacheSymptom = findViewById<TextView>(R.id.headacheSymptom)
        val soreThroatSymptom = findViewById<TextView>(R.id.soreThroatSymptom)
        val tirednessSymptom = findViewById<TextView>(R.id.tirednessSymptom)
        val bodyAchesSymptom = findViewById<TextView>(R.id.bodyAchesSymptom)
        val runnyNoseSymptom = findViewById<TextView>(R.id.runnyNoseSymptom)
        val nauseaSymptom = findViewById<TextView>(R.id.nauseaSymptom)
        val advices = findViewById<TextView>(R.id.advices)

        advices.movementMethod  = ScrollingMovementMethod()

        database.child(currentUserID).get().addOnSuccessListener {
            difficultyBreathingSymptom.text = "Otezano disanje\n/kratkoca daha: " + it.child("day$specificDayResults").child("difficultyBreathing").value.toString()
            mobilitySymptom.text = "Gubitak govora/\nkretanja: " + it.child("day$specificDayResults").child("mobility").value.toString()
            chestPainSymptom.text = "Bol u prsima: " + it.child("day$specificDayResults").child("chestPain").value.toString()
            lossOfSenseSymptom.text = "Gubitak osjeta njuha\n/okusa: " + it.child("day$specificDayResults").child("lossOfSense").value.toString()
            temperatureSymptom.text = "Temperatura: " + it.child("day$specificDayResults").child("temperature").value.toString()
            coughSymptom.text = "Kašalj: " + it.child("day$specificDayResults").child("cough").value.toString()
            headacheSymptom.text = "Glavobolja: " + it.child("day$specificDayResults").child("headache").value.toString()
            soreThroatSymptom.text = "Bol u grlu: " + it.child("day$specificDayResults").child("soreThroat").value.toString()
            tirednessSymptom.text = "Iscrpljenost: " + it.child("day$specificDayResults").child("tiredness").value.toString()
            bodyAchesSymptom.text = "Bolovi u tijelu: " + it.child("day$specificDayResults").child("bodyAches").value.toString()
            runnyNoseSymptom.text = "Začepljeni nos\n/curenje iz nosa: " + it.child("day$specificDayResults").child("runnyNose").value.toString()
            nauseaSymptom.text = "Mučnina: " + it.child("day$specificDayResults").child("nausea").value.toString()
            advices.text = "Preporuke: " + it.child("day$specificDayResults").child("preporuke").value.toString()

        }.addOnFailureListener {
            difficultyBreathingSymptom.text = "Nepoznat parametar."
            mobilitySymptom.text = "Nepoznat parametar."
            chestPainSymptom.text = "Nepoznat parametar."
            lossOfSenseSymptom.text = "Nepoznat parametar."
            temperatureSymptom.text = "Nepoznat parametar."
            coughSymptom.text = "Nepoznat parametar."
            headacheSymptom.text = "Nepoznat parametar."
            soreThroatSymptom.text = "Nepoznat parametar."
            tirednessSymptom.text = "Nepoznat parametar."
            bodyAchesSymptom.text = "Nepoznat parametar."
            runnyNoseSymptom.text = "Nepoznat parametar."
            nauseaSymptom.text = "Nepoznat parametar."
            advices.text = "Nepoznat parametar"
        }



        goBackButton.setOnClickListener {
            startActivity(Intent(this, PollResultsActivity::class.java))
            finish()
        }




    }
}