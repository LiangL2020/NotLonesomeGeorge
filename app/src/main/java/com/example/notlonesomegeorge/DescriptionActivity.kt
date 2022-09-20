package com.example.notlonesomegeorge

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DescriptionActivity: AppCompatActivity(){

    var activityTag = "activityTag"

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)
        Log.i(activityTag, "In onCreate() DescriptionActivity...")

        val tortoise_name = intent.getStringExtra("friend_name_to_description")
        val display = findViewById<TextView>(R.id.skills_name).apply {
            text = tortoise_name
        }
    }
}