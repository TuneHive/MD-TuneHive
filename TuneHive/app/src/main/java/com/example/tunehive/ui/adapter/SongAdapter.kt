package com.example.tunehive.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tunehive.R
import com.example.tunehive.data.response.ListMusicResponseItem

class SongAdapter : ListAdapter<ListMusicResponseItem, SongAdapter.SongViewHolder>(DIFF_CALLBACK) {

    private var onItemClickListener: ((ListMusicResponseItem) -> Unit)? = null

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ListMusicResponseItem>() {
            override fun areItemsTheSame(oldItem: ListMusicResponseItem, newItem: ListMusicResponseItem): Boolean {
                return oldItem.id == newItem.id // Assuming `id` is unique for each song
            }

            override fun areContentsTheSame(oldItem: ListMusicResponseItem, newItem: ListMusicResponseItem): Boolean {
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

        fun bind(song: ListMusicResponseItem) {
            // Set data to the views
            albumTitle.text = song.name ?: "Unknown Title"
            artistName.text = song.singer?.username ?: "Unknown Artist"

            val coverUrl = song.coverUrl
            if (!coverUrl.isNullOrEmpty()) {
                Glide.with(itemView.context)
                    .load(coverUrl)
                    .placeholder(R.drawable.sample_album_cover)
                    .error(R.drawable.sample_album_cover)
                    .into(albumCover)
            } else {
                albumCover.setImageResource(R.drawable.sample_album_cover)
            }

            itemView.setOnClickListener {
                onItemClickListener?.invoke(song)
            }
        }
    }

    fun setOnItemClickListener(listener: (ListMusicResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}
