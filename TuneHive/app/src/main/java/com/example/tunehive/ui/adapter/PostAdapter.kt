package com.example.tunehive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tunehive.R
import com.example.tunehive.data.response.Post

class PostAdapter : ListAdapter<Post, PostAdapter.PostViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((Post) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                // Define the logic to compare items uniquely (e.g., post ID)
                return oldItem.userName == newItem.userName && oldItem.timestamp == newItem.timestamp
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                // Define the logic to compare the contents of two items
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post_community, parent, false)
        return PostViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }

    inner class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userAvatar: ImageView = itemView.findViewById(R.id.profilePicture)
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val postTimestamp: TextView = itemView.findViewById(R.id.postTimestamp)
        private val postText: TextView = itemView.findViewById(R.id.postText)

        fun bind(post: Post) {
            userName.text = post.userName
            postTimestamp.text = post.timestamp
            postText.text = post.text

            // Assuming you have an image resource for the user's avatar
            userAvatar.setImageResource(R.drawable.ic_person_white)  // Replace with actual image source

            itemView.setOnClickListener {
                onItemClickListener?.invoke(post)
            }
        }
    }

    fun setOnItemClickListener(listener: (Post) -> Unit) {
        onItemClickListener = listener
    }
}
