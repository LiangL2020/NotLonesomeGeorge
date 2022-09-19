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
    var activityTag = "activityTag";
    private lateinit var achRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        Log.i(activityTag, "In onCreate() AchievementsActivity...")

        // SET UP RECYCLER VIEW FOR ACHIEVEMENTS
        achRecyclerView = findViewById(R.id.achievements_recycler)
        achRecyclerView.layoutManager = LinearLayoutManager(this)
        achRecyclerView.adapter = AchievementAdapter(this, getAchievementList())
    }

    // PROVIDE DATA SOURCE FOR ACHIEVEMENT LIST
    private fun getAchievementList(): List<Achievement> {
        Log.i(activityTag, "In getAchievementList() AchievementsActivity...")

        var name: String
        val achievements = ArrayList<Achievement>()
        val names = arrayOf<String>("Aimee", "Sosa", "Zoya", "Byrd", "Elize", "Benjamin",
            "Josef", "Downes", "Roscoe", "Wall", "Aj", "Owens", "Renae", "Beaumont", "Jobe",
            "Morales", "Murphy", "Moon", "Riccardo", "Hodge")

        // CREATE 20 ACHIEVEMENTS
        for (i in 1..20){
            name = names[i-1]
            achievements.add(Achievement(name))
        }
        return achievements
    }

}