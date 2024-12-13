package com.example.tunehive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tunehive.R
import com.example.tunehive.data.response.CommentResponseItem

class CommentAdapter : ListAdapter<CommentResponseItem, CommentAdapter.CommentViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CommentResponseItem>() {
            override fun areItemsTheSame(oldItem: CommentResponseItem, newItem: CommentResponseItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CommentResponseItem, newItem: CommentResponseItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_post_community, parent, false) // Use a custom layout for comments
        return CommentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = getItem(position)
        holder.bind(comment)
    }

    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val userAvatar: TextView = itemView.findViewById(R.id.profilePicture)
        private val userName: TextView = itemView.findViewById(R.id.userName)
        private val commentText: TextView = itemView.findViewById(R.id.postText)

        fun bind(comment: CommentResponseItem) {
            userAvatar.text = comment.user?.fullname?.first().toString() // Bind the user avatar
            userName.text = comment.user?.fullname // Bind the user name
            commentText.text = comment.content // Bind the comment text
        }
    }
}
