package com.example.notlonesomegeorge

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //DECLARE VARIABLES
    private lateinit var achBTN: ImageButton
    private lateinit var skillBTN: ImageButton
    private lateinit var georgBTN: ImageButton
    private lateinit var cameraBTN: ImageButton
    private lateinit var hamburgerBTN : ImageButton
    private lateinit var wdn: TextView
    private lateinit var exp: ProgressBar
    private lateinit var LV: TextView

    var activityTag = "activityTag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(activityTag, "In onCreate() MainActivity...")

        // Image button clicked animate
        wdn = findViewById(R.id.wealth_dandelion_num)
        georgBTN = findViewById(R.id.george_image_button)

        var timesClicked = 0

        georgBTN.setOnClickListener {
            Toast.makeText(this,"+1",Toast.LENGTH_SHORT ).show()
            Log.i(activityTag, "George is clicked; dandelion should be added...")
            georgBTN.animate().apply {
                duration = 100
                rotationYBy(360f)
            }.start()

            timesClicked += 1
            //wealth_dandelion_num.text = "$" + timesClicked.toString()

            wdn.text = "$" + timesClicked.toString()

            exp = findViewById(R.id.exp_bar)
            exp.max = 10

            val currentProgress = timesClicked / 10 % 10

            LV = findViewById(R.id.level_display)
            LV.text = "Lv: " + timesClicked / 100

            ObjectAnimator.ofInt(exp,"progress",currentProgress)
                .setDuration(2000)
                .start()




        }

        // INITIATE VARIABLES
        achBTN = findViewById(R.id.tortoiseBTN)
        skillBTN = findViewById(R.id.hatBTN)
        cameraBTN = findViewById(R.id.cameraBTN)
        hamburgerBTN = findViewById(R.id.hamburgerBTN)

        // DIRECT USER TO ACHIEVEMENTS ACTIVITY
        achBTN.setOnClickListener{
            Log.i(activityTag, "Directing to ACHIEVEMENTS activity...")
            call_ach()
        }

        // DIRECT USER TO CAMERA ACTIVITY
        cameraBTN.setOnClickListener{
            Log.i(activityTag, "Directing to CAMERA activity...")
            call_camera()
        }

        hamburgerBTN.setOnClickListener{
            Log.i(activityTag, "Directing to NetWork activity...")
            call_Network()
        }


    }

    // DIRECT USER TO ACHIEVEMENTS ACTIVITY
    private fun call_ach(){
        Log.i(activityTag, "In call_ach()...")
        val intent = Intent(this, AchievementsActivity::class.java)
        startActivity(intent)
    }

    // DIRECT USER TO CAMERA ACTIVITY
    private fun call_camera(){
        Log.i(activityTag, "In call_camera()...")
        val intent = Intent(this, CameraActivity::class.java)
        startActivity(intent)
    }

    private fun call_Network(){
        Log.i(activityTag, "In call_network()...")
        val intent = Intent(this, NetWorkActivity::class.java)
        startActivity(intent)
    }

}