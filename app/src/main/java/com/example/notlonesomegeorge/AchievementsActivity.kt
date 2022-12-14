package com.example.notlonesomegeorge

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "activityTag";

class AchievementsActivity: AppCompatActivity() {

    val names = arrayOf<String>("Aimee", "Sosa", "Zoya", "Byrd", "Elize", "Benjamin",
        "Josef", "Downes", "Roscoe", "Wall", "Aj", "Owens", "Renae", "Beaumont", "Jobe",
        "Morales", "Murphy", "Moon", "Riccardo", "Hodge")

    private lateinit var achRecyclerView: RecyclerView
    private lateinit var addAchBTN: Button
    private lateinit var achievements: ArrayList<Achievement>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        Log.i(TAG, "In onCreate() AchievementsActivity...")

        // DEFINE VARIABLES
        addAchBTN = findViewById(R.id.addAchievementBTN)

        // SET UP RECYCLER VIEW FOR ACHIEVEMENTS
        achRecyclerView = findViewById(R.id.achievements_recycler)
        achRecyclerView.layoutManager = LinearLayoutManager(this)
        achievements = getAchievementList()
        achRecyclerView.adapter = AchievementAdapter(this, achievements)

        addAchBTN.setOnClickListener {
            Log.i(TAG, "add achievement button clicked...")
            achievements = getAchievementList(achievements= achievements)
            achRecyclerView.adapter = AchievementAdapter(this, achievements)
        }
    }

    // PROVIDE DATA SOURCE FOR ACHIEVEMENT LIST
    private fun getAchievementList(): ArrayList<Achievement> {
        Log.i(TAG, "In getAchievementList() AchievementsActivity...")

        var name: String
        val achievements = ArrayList<Achievement>()

        // CREATE x ACHIEVEMENTS
        for (i in 1..5){
            name = names[i-1]
            achievements.add(Achievement(name))
        }
        return achievements
    }

    private fun getAchievementList(achievements: ArrayList<Achievement>): ArrayList<Achievement> {
        Log.i(TAG, "In getAchievementList() AchievementsActivity...")

        var name: String

        // ADD ACHIEVEMENT TO LIST
        name = names[achievements.size]
        achievements.add(Achievement(name))

        return achievements
    }

}