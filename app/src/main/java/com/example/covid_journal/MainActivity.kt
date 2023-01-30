package com.example.covid_journal

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = Firebase.auth
        val newAccountButton = findViewById<Button>(R.id.newAccountButton)
        val login = findViewById<Button>(R.id.loginButton)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val forgotPassword = findViewById<TextView>(R.id.forgotPassword)
        var forgottenPasswordEmail : String = ""

        forgotPassword.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val forgotPasswordLayout = inflater.inflate(R.layout.forgot_password_layout,null)
            val enterEmail = forgotPasswordLayout.findViewById<EditText>(R.id.enterEmail)

            with(builder){
                setTitle("Unesite e-mail!")
                setPositiveButton("Pošalji!"){ dialog, which ->
                    forgottenPasswordEmail = enterEmail.text.toString()

                    auth.sendPasswordResetEmail(forgottenPasswordEmail).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(baseContext,"E-mail poslan!",Toast.LENGTH_SHORT).show()
                        }
                        else
                            Toast.makeText(baseContext,"Nije uspijelo, pokušajte ponovo.",Toast.LENGTH_SHORT).show()
                    }


                }
                setNegativeButton("Odustani"){ dialog, which ->
                    Log.d("Main","Resetiranje šifre prekinuto!")
                }
                setView(forgotPasswordLayout)
                show()
            }
        }

        newAccountButton.setOnClickListener {
            startActivity(Intent(this, UserRegistration::class.java))
            finish()
        }

        login.setOnClickListener {

            if (email.text.toString().isEmpty()) {
                email.error = "Unesite email"
                return@setOnClickListener
            }

            if(password.text.toString().isEmpty()){
                password.error = "Unesite šifru"
                return@setOnClickListener
            }


            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Toast.makeText(
                            baseContext, "Prijava neuspješna. Pokušajte ponovo. ",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }

        }
    }




    public override fun onStart() {
            super.onStart()
            val currentUser= auth.currentUser
            if(currentUser!=null){
                updateUI(currentUser)
            }


        }



    private fun updateUI(currentUser: FirebaseUser?) {

            if (currentUser != null) {
                startActivity(Intent(this, Account::class.java))
                finish()
            } else {
                Toast.makeText(
                    baseContext, "Prijavite se.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }


}