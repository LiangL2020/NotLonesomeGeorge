package com.example.notlonesomegeorge

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AchievementsActivity: AppCompatActivity() {

    // DECLARE VARIABLES
    var achActivityTag = "mainActivityTag";
    private lateinit var achRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        Log.i(achActivityTag, "In onCreate() AchievementsActivity...")

        // SET UP RECYCLER VIEW FOR ACHIEVEMENTS
        achRecyclerView = findViewById(R.id.achievements_recycler)
        achRecyclerView.layoutManager = LinearLayoutManager(this)
        achRecyclerView.adapter = AchievementAdapter(this, getAchievementList())
    }

    // PROVIDE DATA SOURCE FOR ACHIEVEMENT LIST
    private fun getAchievementList(): List<Achievement> {

        var name: String
        val achievements = ArrayList<Achievement>()

        // CREATE 50 ACHIEVEMENTS
        for (i in 1..50){
            name = getString(R.string.friend_tortoise_name)
            achievements.add(Achievement(name))
            Log.i(achActivityTag,name)
        }

        return achievements
    }

}