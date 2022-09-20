package com.example.notlonesomegeorge

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "activityTag"

/**
 * This is an important class that sits in between RecyclerView and the itemView
 * HomeworkAdapter provides a binding from an app-specific data set (homework) to views
 * that are displayed within a RecyclerView.
 */
class AchievementAdapter(val activity: Activity, private val achievements: List<Achievement>) :
    RecyclerView.Adapter<AchievementAdapter.ViewHolder>() {
    /**
     * Called when RecyclerView **needs** a new RecyclerView.ViewHolder of the given type to represent an item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_achievement, parent, false)
        return ViewHolder(view)
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the RecyclerView.ViewHolder.itemView
     * to reflect the item at the given position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cAch = achievements[position]
        holder.bind(cAch)
    }

    override fun getItemCount(): Int {
        return achievements.size
    }

    /**
     * This class manages the itemView
     * an itemView can have more than one view object
     */
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val friendIMG: ImageView
        private val friendName: TextView
        private val launchButton: Button
        private val cardView: CardView

        /**
         * this bind function takes the current homework from [onBindViewHolder]
         * this is where we can manipulate how the view objects look and behave
         */
        fun bind(cAch: Achievement) {
            friendName.text = cAch.name
            launchButton.setOnClickListener {
                Log.d(TAG, "button pressed for " + cAch.name)
                val intent = Intent(activity, DescriptionActivity::class.java).also {
                    it.putExtra("friend_name_to_description", cAch.name)
                    activity.startActivity(it)
                }
            }

            cardView.setOnLongClickListener {
                Toast.makeText(activity,"long clicked", Toast.LENGTH_SHORT).show()
                true
            }
        }

        // obtaining the references to various view objects we want to manipulate
        init {
            friendIMG = itemView.findViewById(R.id.item_friend_img)
            friendName = itemView.findViewById(R.id.item_friend_name)
            launchButton = itemView.findViewById(R.id.item_friend_description_btn)
            cardView = itemView.findViewById(R.id.achievementsCard)
        }
    }
}