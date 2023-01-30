package com.example.covid_journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase


private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val currentUserID = auth.currentUser?.uid.toString()
        val ime = findViewById<TextView>(R.id.pName)
        val dob = findViewById<TextView>(R.id.pAge)
        val cijepljenost = findViewById<TextView>(R.id.pVaccine)
        val spol = findViewById<TextView>(R.id.pGender)
        val povijestBolesti = findViewById<TextView>(R.id.pDiseaseHistory)

        var cancerString : String
        var cvdString : String
        var hivString : String
        var chronicKidneyString : String
        var chronicLiverString : String
        var diabetesString : String
        var downSyndromeString : String
        var lungDiseaseString : String
        var transplantString : String
        var weakImmuneSystemString : String

        database.child(currentUserID).get().addOnSuccessListener {
                    ime.text = "Ime: "+ it.child("fullName").value.toString()
           }.addOnFailureListener {
                    ime.text = "Nedostupan podatak."
           }

        database.child(currentUserID).get().addOnSuccessListener {
            dob.text = "Dob: "+ it.child("age").value.toString()
        }.addOnFailureListener {
            dob.text = "Nedostupan podatak."
        }

        database.child(currentUserID).get().addOnSuccessListener {
            cijepljenost.text = "Cijepljen/a: "+ it.child("vaccinated").value.toString()
        }.addOnFailureListener {
            cijepljenost.text = "Nedostupan podatak."
        }

        database.child(currentUserID).get().addOnSuccessListener {
            spol.text = "Spol: "+ it.child("gender").value.toString()
        }.addOnFailureListener {
            spol.text = "Nedostupan podatak."
        }

        database.child(currentUserID).get().addOnSuccessListener {

            if(it.child("Diseases").child("Cancer").value==true)
                cancerString = "Rak\n"
            else
                cancerString = ""

            if(it.child("Diseases").child("Cardiovascular disease").value==true)
                cvdString = "Kardiovaskularna bolest\n"
            else
                cvdString = ""

            if(it.child("Diseases").child("HIV").value==true)
                hivString = "HIV\n"
            else
                hivString = ""

            if(it.child("Diseases").child("Chronic kidney disease").value==true)
                chronicKidneyString = "Kronična bolest bubrega\n"
            else
                chronicKidneyString = ""

            if(it.child("Diseases").child("Chronic liver disease").value==true)
                chronicLiverString = "Kronična bolest jetre\n"
            else
                chronicLiverString = ""

            if(it.child("Diseases").child("Diabetes").value==true)
                diabetesString = "Dijabetes\n"
            else
                diabetesString = ""

            if(it.child("Diseases").child("Down syndrome").value==true)
                downSyndromeString = "Down sindrom\n"
            else
                downSyndromeString = ""

            if(it.child("Diseases").child("Lung disease").value==true)
                lungDiseaseString = "Bolesti plućae\n"
            else
                lungDiseaseString = ""

            if(it.child("Diseases").child("Transplant").value==true)
                transplantString = "Transplant organa\n"
            else
                transplantString = ""

            if(it.child("Diseases").child("Weak immune system").value==true)
                weakImmuneSystemString = "Slabi imunosni sustav\n"
            else
                weakImmuneSystemString = ""


            povijestBolesti.text = "Povijest bolesti: \n\n" + cancerString + cvdString + hivString + chronicKidneyString + chronicLiverString +
             diabetesString + downSyndromeString + lungDiseaseString + transplantString + weakImmuneSystemString

        }.addOnFailureListener {
            spol.text = "Nedostupan podatak."
        }





        val goBackFromProfileButton = findViewById<Button>(R.id.goBackFromProfileButton)
        goBackFromProfileButton.setOnClickListener {
            startActivity(Intent(this, Account::class.java))
            finish()
        }





    }



    }


