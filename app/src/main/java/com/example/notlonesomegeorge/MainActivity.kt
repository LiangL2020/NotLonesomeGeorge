package com.example.notlonesomegeorge

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //DECLARE VARIABLES
    private lateinit var achBTN: ImageButton
    private lateinit var skillBTN: ImageButton

    var mainActivityTag = "mainActivityTag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(mainActivityTag, "In MainActivity...")

        // INITIATE VARIABLES
        achBTN = findViewById(R.id.tortoiseBTN)
        skillBTN = findViewById(R.id.hatBTN)

        // DIRECT USER TO ACHIEVEMENTS ACTIVITY
        achBTN.setOnClickListener{
            Log.i(mainActivityTag, "Directing to ACHIEVEMENTS activity...")
            call_ach()
        }
    }

    // DIRECT USER TO ACHIEVEMENTS ACTIVITY
    private fun call_ach(){
        Log.i(mainActivityTag, "In call_ach()...")
        val intent = Intent(this, AchievementsActivity::class.java)
        startActivity(intent)
    }

}