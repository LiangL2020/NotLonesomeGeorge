package com.example.notlonesomegeorge

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnToLogin: TextView

    // create Firebase authentication object
    private lateinit var auth: FirebaseAuth

    // creating a variable for our
    // Firebase Database.
    private lateinit var firebaseDatabase: FirebaseDatabase
    // creating a variable for our Database
    // Reference for Firebase.
    private lateinit var databaseReference: DatabaseReference
    private lateinit var userInfo: UserInfo


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // View Bindings
        etEmail = findViewById(R.id.etSEmailAddress)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        btnSignUp = findViewById(R.id.btnSignUp)
        btnToLogin = findViewById(R.id.btnToLogin)

        // Initialising auth object
        auth = Firebase.auth

        // instance of our FIrebase database.
        firebaseDatabase = FirebaseDatabase.getInstance()
        // below line is used to get reference for our database.
        databaseReference = firebaseDatabase.getReference("UserInfo")
        // initializing our object
        // class variable.
        userInfo = UserInfo()

        btnSignUp.setOnClickListener {
            signUpUser()
        }

        // switching from signUp Activity to Login Activity
        btnToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun signUpUser() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }else if (pass != confirmPassword) {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }else{
            addDatatoFirebase(email, pass)
        }

        /*
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the email and pass in it.
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            if (it.isSuccessful) {
                Toast.makeText(this, "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
         */
    }

    private fun addDatatoFirebase(email: String, pass: String) {
        // set data in object
        userInfo.setUserEmail(email)
        userInfo.setUserPassword(pass)

        // we are use add value event listener method
        // which is called with database reference.
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // inside the method of on Data change we are setting
                // our object class to our database reference.
                // data base reference will sends data to firebase.
                databaseReference.setValue(userInfo)

                // after adding this data we are showing toast message.
                Toast.makeText(this@RegisterActivity, "data added", Toast.LENGTH_SHORT).show()
            }

            override fun onCancelled(error: DatabaseError) {
                // if the data is not added or it is cancelled then
                // we are displaying a failure toast message.
                Toast.makeText(this@RegisterActivity, "Fail to add data $error", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}
