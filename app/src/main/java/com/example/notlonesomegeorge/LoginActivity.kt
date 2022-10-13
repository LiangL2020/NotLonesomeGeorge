package com.example.notlonesomegeorge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    //DECLARE VARIABLES
    private lateinit var auth: FirebaseAuth;
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnToRegister: Button
    var activityTag = "activityTag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth

        setContentView(R.layout.activity_login)
        Log.i(activityTag, "In onCreate() LoginActivity...")

        // View Binding
        btnToRegister = findViewById(R.id.btnToRegister)
        btnLogin = findViewById(R.id.btnLogin)
        etEmail = findViewById(R.id.etEmailAddress)
        etPass = findViewById(R.id.etPassword)

        // initialising Firebase auth object
        auth = FirebaseAuth.getInstance()

        btnLogin.setOnClickListener {
            login()
        }

        btnToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            // using finish() to end the activity
            finish()
        }
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Logged-In Successfully", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log-In failed ", Toast.LENGTH_SHORT).show()
        }
    }
}