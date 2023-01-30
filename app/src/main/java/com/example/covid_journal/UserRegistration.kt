package com.example.covid_journal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private lateinit var auth: FirebaseAuth
private lateinit var database: DatabaseReference

class UserRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        auth = Firebase.auth
        database = FirebaseDatabase.getInstance("https://covid-journal-e4523-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")

        val register = findViewById<Button>(R.id.registerButton)
        val password = findViewById<EditText>(R.id.password)
        val confirmPassword = findViewById<EditText>(R.id.confirmPassword)
        val email = findViewById<EditText>(R.id.email)
        val goBackFromRegistration = findViewById<Button>(R.id.goBackFromRegistration)

        goBackFromRegistration.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }
        //Kada se korisnik registrira vraća ga na main menu, ukoliko ne uspije izbacuje se poruka
        //NAŠTIMATI UVJETE
        register.setOnClickListener {

            if(email.text.toString().isEmpty() && password.text.toString().isEmpty() && confirmPassword.text.toString().isEmpty() )
            {
                email.error = "Unesite email"
                password.error = "Unesite šifru"
                confirmPassword.error = "Ponovite šifru"
                Toast.makeText(baseContext, "Unesite podatke",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if(password.text.toString()!=confirmPassword.text.toString())
            {
                Toast.makeText(baseContext, "Šifre se ne podudaraju. Pokušajte ponovo.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(email.text.toString().isEmpty())
            {
                Toast.makeText(baseContext, "Unesite adresu.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(password.text.toString().isEmpty())
            {
                password.error = "Unesite šifru"
                return@setOnClickListener
            }

            if(!(email.text.toString().contains(".com") || email.text.toString().contains(".hr")))
            {
                email.error = "Unesite valjanu adresu"
                return@setOnClickListener
            }

            if(password.text.toString().length<8){
                password.error = "Šifra mora biti barem 8 znakova."
                return@setOnClickListener
            }

            if(confirmPassword.text.toString().isEmpty())
            {
                confirmPassword.error = "Niste unijeli ponovo šifru."
                return@setOnClickListener
            }



            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            var dayCounter = 1;
                            var firstSubmit = false;
                            val user = User(email.text.toString(),password.text.toString(),firstSubmit, dayCounter)

                            database.child(auth.currentUser?.uid.toString()).setValue(user)
                            startActivity(Intent(this,MainActivity::class.java))
                            auth.signOut()
                            finish()
                        } else {
                            Toast.makeText(baseContext, "Registracija nije uspijela, račun već postoji u bazi podataka.",
                                Toast.LENGTH_SHORT).show()
                        }
                    }


        }



    }
}