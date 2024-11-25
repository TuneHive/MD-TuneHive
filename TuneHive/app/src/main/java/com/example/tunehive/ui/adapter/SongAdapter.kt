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
import com.example.tunehive.ui.main.home.Song

class SongAdapter : ListAdapter<Song, SongAdapter.SongViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((Song) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Song>() {
            override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem.name == newItem.name && oldItem.artist == newItem.artist
            }

            override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_song, parent, false)
        return SongViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = getItem(position)
        holder.bind(song)
    }

    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val albumCover: ImageView = itemView.findViewById(R.id.albumCover)
        private val albumTitle: TextView = itemView.findViewById(R.id.albumTitle)
        private val artistName: TextView = itemView.findViewById(R.id.artistName)

        fun bind(song: Song) {
            // Set data to the views
            albumTitle.text = song.name
            artistName.text = song.artist

            // Assuming you have an image resource for the album cover
            albumCover.setImageResource(R.drawable.sample_album_cover)  // Replace with actual image source

            itemView.setOnClickListener {
                onItemClickListener?.invoke(song)
            }
        }
    }

    fun setOnItemClickListener(listener: (Song) -> Unit) {
        onItemClickListener = listener
    }
}
