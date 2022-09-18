package com.example.notlonesomegeorge

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class AchievementsActivity: AppCompatActivity() {

    var achActivityTag = "mainActivityTag";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        Log.i(achActivityTag, "In onCreate() AchievementsActivity...")

    }
}