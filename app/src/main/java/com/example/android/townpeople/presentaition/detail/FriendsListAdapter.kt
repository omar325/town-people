package com.example.android.townpeople.presentaition.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.townpeople.R

class FriendsListAdapter(
    private val friends: List<String>
): RecyclerView.Adapter<FriendsListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.friend_item, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(friends[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(friend: String) {
            itemView.findViewById<TextView>(R.id.name).text = friend
        }
    }

    override fun getItemCount(): Int = friends.size

}