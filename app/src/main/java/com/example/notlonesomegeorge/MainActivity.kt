package com.example.notlonesomegeorge

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    //DECLARE VARIABLES
    private lateinit var achBTN: ImageButton
    private lateinit var skillBTN: ImageButton
    private lateinit var georgBTN: ImageButton

    var activityTag = "activityTag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(activityTag, "In onCreate() MainActivity...")

        //Iamge button clicked animate


        georgBTN = findViewById(R.id.george_image_button)
        georgBTN.setOnClickListener {

            georgBTN.animate().apply {
                duration = 100
                rotationYBy(360f)
            }.start()



        }

        // INITIATE VARIABLES
        achBTN = findViewById(R.id.tortoiseBTN)
        skillBTN = findViewById(R.id.hatBTN)

        // DIRECT USER TO ACHIEVEMENTS ACTIVITY
        achBTN.setOnClickListener{
            Log.i(activityTag, "Directing to ACHIEVEMENTS activity...")
            call_ach()
        }
    }

    // DIRECT USER TO ACHIEVEMENTS ACTIVITY
    private fun call_ach(){
        Log.i(activityTag, "In call_ach()...")
        val intent = Intent(this, AchievementsActivity::class.java)
        startActivity(intent)
    }

}