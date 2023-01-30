package com.example.covid_journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference
private lateinit var currentUser : User


class Account : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val signOut = findViewById<Button>(R.id.signOutButton)
        val profileButton = findViewById<Button>(R.id.profileButton)
        val currentUserID = auth.currentUser?.uid.toString()
        var firstSubmit = false
        var dayCounter = 1
        //Tipke za dane

        val firstDay = findViewById<Button>(R.id.dayOneButton)
        val secondDay = findViewById<Button>(R.id.dayTwoButton)
        val thirdDay = findViewById<Button>(R.id.dayThreeButton)
        val fourthDay = findViewById<Button>(R.id.dayFourButton)
        val fifthDay = findViewById<Button>(R.id.dayFiveButton)
        val sixthDay = findViewById<Button>(R.id.daySixButton)
        val seventhDay = findViewById<Button>(R.id.daySevenButton)
        val eightDay = findViewById<Button>(R.id.dayEightButton)
        val ninthDay = findViewById<Button>(R.id.dayNineButton)
        val tenthDay = findViewById<Button>(R.id.dayTenButton)
        val resultsButton = findViewById<Button>(R.id.resultsButton)

        resultsButton.setOnClickListener {
            startActivity(Intent(this, PollResultsActivity::class.java))
            finish()
        }

        database.child(currentUserID).get().addOnSuccessListener {
            dayCounter = it.child("dayCounter").value.toString().toInt()
        }.addOnFailureListener {
            dayCounter = 1
        }


        //Za svaki dan treba onemogućit ukoliko je već popunjen, ili ne dozvoliti ulazak nakon već popunjavanja

        firstDay.setOnClickListener {

            if(dayCounter>1){
                firstDay.isEnabled = false
                firstDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 1. dan!",Toast.LENGTH_SHORT).show()
            }

            if(firstSubmit==true && dayCounter == 1){

                startActivity(Intent(this, PollActivity::class.java))
                finish()

            }
            else if(firstSubmit==false)
            {
                firstDay.isEnabled = false
                firstDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas da kreirate profil!",Toast.LENGTH_SHORT).show()

            }

        }

        secondDay.setOnClickListener {

            if(dayCounter>2){
                secondDay.isEnabled = false
                secondDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 2. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 2){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 2)
            {
                secondDay.isEnabled = false
                secondDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 1. dan",Toast.LENGTH_SHORT).show()
            }

        }

        thirdDay.setOnClickListener {

            if(dayCounter>3){
                thirdDay.isEnabled = false
                thirdDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 3. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 3){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 3)
            {
                thirdDay.isEnabled = false
                thirdDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 2. dan",Toast.LENGTH_SHORT).show()
            }
        }


            fourthDay.setOnClickListener {

                if(dayCounter>4){
                    fourthDay.isEnabled = false
                    fourthDay.isClickable = false
                    Toast.makeText(baseContext,"Već ste ispunili 4. dan!",Toast.LENGTH_SHORT).show()
                }

                if(dayCounter == 4){
                    startActivity(Intent(this, PollActivity::class.java))
                    finish()
                }
                else if(dayCounter < 4)
                {
                    fourthDay.isEnabled = false
                    fourthDay.isClickable = false
                    Toast.makeText(baseContext,"Molimo vas prvo ispunite 3. dan",Toast.LENGTH_SHORT).show()
                }
            }

        fifthDay.setOnClickListener {

            if(dayCounter>5){
                fifthDay.isEnabled = false
                fifthDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 5. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 5){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 5)
            {
                fifthDay.isEnabled = false
                fifthDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 4. dan",Toast.LENGTH_SHORT).show()
            }
        }

        sixthDay.setOnClickListener {

            if(dayCounter>6){
                sixthDay.isEnabled = false
                sixthDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 6. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 6){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 6)
            {
                sixthDay.isEnabled = false
                sixthDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 5. dan",Toast.LENGTH_SHORT).show()
            }
        }


        seventhDay.setOnClickListener {

            if(dayCounter>7){
                seventhDay.isEnabled = false
                seventhDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 7. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 7){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 7)
            {
                seventhDay.isEnabled = false
                seventhDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 6. dan",Toast.LENGTH_SHORT).show()
            }
        }

        eightDay.setOnClickListener {

            if(dayCounter>8){
                eightDay.isEnabled = false
                eightDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 8. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 8){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 8)
            {
                eightDay.isEnabled = false
                eightDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 7. dan",Toast.LENGTH_SHORT).show()
            }
        }

        ninthDay.setOnClickListener {

            if(dayCounter>9){
                ninthDay.isEnabled = false
                ninthDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 9. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 9){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 9)
            {
                ninthDay.isEnabled = false
                ninthDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 8. dan",Toast.LENGTH_SHORT).show()
            }
        }


        tenthDay.setOnClickListener {

            if(dayCounter>10){
                tenthDay.isEnabled = false
                tenthDay.isClickable = false
                Toast.makeText(baseContext,"Već ste ispunili 10. dan!",Toast.LENGTH_SHORT).show()
            }

            if(dayCounter == 10){
                startActivity(Intent(this, PollActivity::class.java))
                finish()
            }
            else if(dayCounter < 10)
            {
                tenthDay.isEnabled = false
                tenthDay.isClickable = false
                Toast.makeText(baseContext,"Molimo vas prvo ispunite 9. dan",Toast.LENGTH_SHORT).show()
            }
        }









        //firstSubmit je false u početku tako da ako korisnik pokuša ući u anketu prije kreiranja profila neće mu dozvoliti
        //Kada se profil popuni, u bazi će se promijeniti firstSubmit na true

        database.child(currentUserID).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    currentUser = snapshot.getValue(User::class.java)!!
                    firstSubmit = currentUser.firstSubmit
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("FAIL", error.message)
            }


        })

        signOut.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this,MainActivity::class.java))
        }


        profileButton.setOnClickListener {
            if(firstSubmit==true) {
                startActivity(Intent(this, ProfileActivity::class.java))
                finish()
            }
            else{
            startActivity(Intent(this, ProfileCreationActivity::class.java))
            finish()
            }
        }

    }
}