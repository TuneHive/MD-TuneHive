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
import com.example.tunehive.data.response.PostResponseItem

class PostAdapter : ListAdapter<PostResponseItem, PostAdapter.PostViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((PostResponseItem) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<PostResponseItem>() {
            override fun areItemsTheSame(oldItem: PostResponseItem, newItem: PostResponseItem): Boolean {
                return oldItem.id == newItem.id // Compare by post ID
            }

            override fun areContentsTheSame(oldItem: PostResponseItem, newItem: PostResponseItem): Boolean {
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
        private val likeCount: TextView = itemView.findViewById(R.id.likeCount) // Added like count TextView
        private val commentButton: ImageView = itemView.findViewById(R.id.comment_button)

        fun bind(post: PostResponseItem) {
            userName.text = post.user?.fullname
            postTimestamp.text = post.updatedAt
            postText.text = post.content
            likeCount.text = post.likeCount.toString() // Bind the like count

            // Set user avatar (use actual avatar image if available)
            userAvatar.setImageResource(R.drawable.ic_person_white)

            itemView.setOnClickListener {
                onItemClickListener?.invoke(post)
            }

            commentButton.setOnClickListener {
                onItemClickListener?.invoke(post) // Handle comment button click
            }
        }
    }

    fun setOnItemClickListener(listener: (PostResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}
