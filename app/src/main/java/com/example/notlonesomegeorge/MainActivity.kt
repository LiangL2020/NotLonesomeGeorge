package com.example.notlonesomegeorge

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var achRecyclerView: RecyclerView


    var mainActivityTag = "mainActivityTag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(mainActivityTag, "In onCreate() MainActivity...")

        // INITIATE VARIABLES
        achBTN = findViewById(R.id.tortoiseBTN)
        skillBTN = findViewById(R.id.hatBTN)

        // SET UP RECYCLER VIEW FOR ACHIEVEMENTS
        achRecyclerView = findViewById(R.id.achievements_recycler)
        achRecyclerView.layoutManager = LinearLayoutManager(this)
        achRecyclerView.adapter = AchievementAdapter(this, getAchievementList())

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

    // PROVIDE DATA SOURCE FOR ACHIEVEMENT LIST
    private fun getAchievementList(): List<Achievement> {

        var name: String
        val achievements = ArrayList<Achievement>()

        // create 100 homework!
        for (i in 1..100){
            name = getString(R.string.friend_tortoise_name)
            achievements.add(Achievement(name))
        }

        return achievements
    }

}