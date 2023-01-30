package com.example.covid_journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference

class PollResultsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_poll_results)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val currentUserID = auth.currentUser?.uid.toString()
        var dayCounter = 1
        database.child(currentUserID).get().addOnSuccessListener {
            dayCounter = it.child("dayCounter").value.toString().toInt()
        }.addOnFailureListener {
            dayCounter = 1
        }


        val goBackButton = findViewById<Button>(R.id.goBackFromPollResultsButton)
        val dayOneResultsButton = findViewById<Button>(R.id.dayOneResultsButton)
        val dayTwoResultsButton = findViewById<Button>(R.id.dayTwoResultsButton)
        val dayThreeResultsButton = findViewById<Button>(R.id.dayThreeResultsButton)
        val dayFourResultsButton = findViewById<Button>(R.id.dayFourResultsButton)
        val dayFiveResultsButton = findViewById<Button>(R.id.dayFiveResultsButton)
        val daySixResultsButton = findViewById<Button>(R.id.daySixResultsButton)
        val daySevenResultsButton = findViewById<Button>(R.id.daySevenResultsButton)
        val dayEightResultsButton = findViewById<Button>(R.id.dayEightResultsButton)
        val dayNineResultsButton = findViewById<Button>(R.id.dayNineResultsButton)
        val dayTenResultsButton = findViewById<Button>(R.id.dayTenResultsButton)




        dayOneResultsButton.setOnClickListener {

            if(dayCounter==1){
                dayOneResultsButton.isEnabled = false
                dayOneResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas prvo ispunite anketu za 1.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>1){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(1)

        }

        dayTwoResultsButton.setOnClickListener {
            if(dayCounter==2 || dayCounter<2){
                dayTwoResultsButton.isEnabled = false
                dayTwoResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 2.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>2){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(2)
        }


        dayThreeResultsButton.setOnClickListener {
            if(dayCounter==3 || dayCounter<3){
                dayThreeResultsButton.isEnabled = false
                dayThreeResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 3.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>3){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(3)
        }


        dayFourResultsButton.setOnClickListener {
            if(dayCounter==4 || dayCounter<4){
                dayFourResultsButton.isEnabled = false
                dayFourResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 4.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>4){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(4)
        }


        dayFiveResultsButton.setOnClickListener {
            if(dayCounter==5 || dayCounter<5){
                dayFiveResultsButton.isEnabled = false
                dayFiveResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 5.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>5){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(5)
        }


        daySixResultsButton.setOnClickListener {
            if(dayCounter==6 || dayCounter<6){
                daySixResultsButton.isEnabled = false
                daySixResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 6.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>6){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(6)
        }


        daySevenResultsButton.setOnClickListener {
            if(dayCounter==7 || dayCounter<7){
                daySevenResultsButton.isEnabled = false
                daySevenResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 7.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>7){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(7)
        }

        dayEightResultsButton.setOnClickListener {
            if(dayCounter==8 || dayCounter<8){
                dayEightResultsButton.isEnabled = false
                dayEightResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 8.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>8){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(8)
        }

        dayNineResultsButton.setOnClickListener {
            if(dayCounter==9 || dayCounter<9){
                dayNineResultsButton.isEnabled = false
                dayNineResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 9.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>9){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(9)
        }


        dayTenResultsButton.setOnClickListener {
            if(dayCounter==10|| dayCounter<10){
                dayTenResultsButton.isEnabled = false
                dayTenResultsButton.isClickable = false
                Toast.makeText(baseContext,"Molimo Vas ispunite anketu za 10.dan!", Toast.LENGTH_SHORT).show()
            }

            if(dayCounter>10){
                startActivity(Intent(this, PollResultsDetailsActivity::class.java))
                finish()
            }

            database.child(currentUserID).child("specificDayResults").setValue(10)
        }







        goBackButton.setOnClickListener {
            startActivity(Intent(this, Account::class.java))
            finish()
        }








    }
}