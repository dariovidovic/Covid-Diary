package com.example.covid_journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference

class ProfileCreationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_creation)


        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")
        val currentUserID = auth.currentUser?.uid.toString()

        val goBackFromProfileCreationButton = findViewById<Button>(R.id.goBackFromProfileCreationButton)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val fullName = findViewById<EditText>(R.id.enterFullName)
        val age = findViewById<EditText>(R.id.enterAge)
        val gender = findViewById<RadioGroup>(R.id.genderGroup)
        val pregnancy = findViewById<RadioGroup>(R.id.pregnancyGroup)
        val smoking = findViewById<RadioGroup>(R.id.smokingGroup)
        val vaccinated = findViewById<RadioGroup>(R.id.vaccineGroup)
        val workplace = findViewById<RadioGroup>(R.id.workplaceGroup)

        val cvd = findViewById<CheckBox>(R.id.cvdBox)
        val hiv = findViewById<CheckBox>(R.id.hivBox)
        val cancer = findViewById<CheckBox>(R.id.cancerBox)
        val diabetes = findViewById<CheckBox>(R.id.diabetesBox)
        val transplant = findViewById<CheckBox>(R.id.transplantBox)
        val lungDisease = findViewById<CheckBox>(R.id.lungDiseaseBox)
        val immuneSystem = findViewById<CheckBox>(R.id.immuneSystemBox)
        val chronicLiver = findViewById<CheckBox>(R.id.chronicLiverBox)
        val chronicKidney = findViewById<CheckBox>(R.id.chronicKidneyBox)
        val downSyndrome = findViewById<CheckBox>(R.id.downSyndromeBox)


        goBackFromProfileCreationButton.setOnClickListener {
            startActivity(Intent(this, Account::class.java))
            finish()
        }

        submitButton.setOnClickListener {

            if(fullName.text.isNullOrEmpty()){
                Toast.makeText(baseContext, "Molimo unesite vaše ime!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("fullName").setValue(fullName.text.toString())

            if(age.text.isNullOrEmpty()){
                Toast.makeText(baseContext, "Molimo unesite vašu dob!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("age").setValue(age.text.toString().toInt())

            val selectedGender = gender.checkedRadioButtonId
            val genderButton = findViewById<RadioButton>(selectedGender)

            val selectedPregnancy = pregnancy.checkedRadioButtonId
            val pregnancyButton = findViewById<RadioButton>(selectedPregnancy)

            val selectedSmoking = smoking.checkedRadioButtonId
            val smokingButton = findViewById<RadioButton>(selectedSmoking)

            val selectedVaccine = vaccinated.checkedRadioButtonId
            val vaccineButton = findViewById<RadioButton>(selectedVaccine)

            val selectedWorkplace = workplace.checkedRadioButtonId
            val workplaceButton = findViewById<RadioButton>(selectedWorkplace)

            //Provjera radio buttona

            if(gender.checkedRadioButtonId == -1){
                Toast.makeText(baseContext, "Molimo označite vaš spol!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("gender").setValue(genderButton.text.toString())

            if(pregnancy.checkedRadioButtonId == -1){
                Toast.makeText(baseContext, "Molimo označite jeste li trudni!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("pregnancy").setValue(pregnancyButton.text.toString())

            if(smoking.checkedRadioButtonId == -1){
                Toast.makeText(baseContext, "Molimo označite jeste li pušač!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("smoking").setValue(smokingButton.text.toString())

            if(vaccinated.checkedRadioButtonId == -1){
                Toast.makeText(baseContext, "Molimo označite jeste li cijepljeni!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("vaccinated").setValue(vaccineButton.text.toString())

            if(workplace.checkedRadioButtonId == -1){
                Toast.makeText(baseContext, "Molimo označite je li vaše radno mjesto visokorizično!",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else
                database.child(currentUserID).child("highRiskWorkplace").setValue(workplaceButton.text.toString())

            //Provjera bolesti

            if(cvd.isChecked)
                database.child(currentUserID).child("Diseases").child("Cardiovascular disease").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Cardiovascular disease").setValue(false)

            if(hiv.isChecked)
                database.child(currentUserID).child("Diseases").child("HIV").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("HIV").setValue(false)

            if(cancer.isChecked)
                database.child(currentUserID).child("Diseases").child("Cancer").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Cancer").setValue(false)

            if(diabetes.isChecked)
                database.child(currentUserID).child("Diseases").child("Diabetes").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Diabetes").setValue(false)

            if(transplant.isChecked)
                database.child(currentUserID).child("Diseases").child("Transplant").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Transplant").setValue(false)

            if(lungDisease.isChecked)
                database.child(currentUserID).child("Diseases").child("Lung disease").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Lung disease").setValue(false)

            if(immuneSystem.isChecked)
                database.child(currentUserID).child("Diseases").child("Weak immune system").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Weak immune system").setValue(false)

            if(chronicLiver.isChecked)
                database.child(currentUserID).child("Diseases").child("Chronic liver disease").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Chronic liver disease").setValue(false)

            if(chronicKidney.isChecked)
                database.child(currentUserID).child("Diseases").child("Chronic kidney disease").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Chronic kidney disease").setValue(false)

            if(downSyndrome.isChecked)
                database.child(currentUserID).child("Diseases").child("Down syndrome").setValue(true)
            else
                database.child(currentUserID).child("Diseases").child("Down syndrome").setValue(false)


            database.child(currentUserID).child("firstSubmit").setValue(true)
            startActivity(Intent(this, Account::class.java))
            finish()


        }



    }






}