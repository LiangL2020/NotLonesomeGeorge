package com.example.notlonesomegeorge

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class LoginActivity : AppCompatActivity() {

    //DECLARE VARIABLES
    private lateinit var auth: FirebaseAuth;
    private lateinit var etEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnToRegister: Button

    // creating a variable for our
    // Firebase Database.
    private lateinit var firebaseDatabase: FirebaseDatabase
    // creating a variable for our Database
    // Reference for Firebase.
    private lateinit var databaseReference: DatabaseReference

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

        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("UserInfo")

        btnLogin.setOnClickListener {
//            login()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        btnToRegister.setOnClickListener {
            Log.i(activityTag, "btnToRegister clicked...")
            call_register()
        }
    }

    // DIRECT USER TO ACHIEVEMENTS ACTIVITY
    private fun call_register(){
        Log.i(activityTag, "In call_register()...")
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun login() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()

        val rootRef = FirebaseDatabase.getInstance().reference
        val userNameRef = rootRef.child("UserEmail").child(email)
        val eventListener: ValueEventListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (!dataSnapshot.exists()) {
                    // Get Post object and use the values to update the UI
                    val post = dataSnapshot.getValue<String>()
                    Log.i(activityTag, post.toString())
                    Toast.makeText(this@LoginActivity, "Please Register", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.i(activityTag, databaseError.message) //Don't ignore errors!
                Toast.makeText(this@LoginActivity, "Error occured", Toast.LENGTH_SHORT).show()
            }
        }
        userNameRef.addListenerForSingleValueEvent(eventListener)

        /*
        // calling signInWithEmailAndPassword(email, pass)
        // function using Firebase auth object
        // On successful response Display a Toast
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Logged-In Successfully", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(this, "Log-In failed ", Toast.LENGTH_SHORT).show()
        }
        */
    }
}