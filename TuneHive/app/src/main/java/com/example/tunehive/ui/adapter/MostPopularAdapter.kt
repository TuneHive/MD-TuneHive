package com.example.tunehive.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tunehive.data.response.ListMusicResponseItem
import com.example.tunehive.databinding.ItemMostPopularBinding

class MostPopularAdapter(
    private val items: List<ListMusicResponseItem>,
    private val onItemClick: (ListMusicResponseItem) -> Unit
) : RecyclerView.Adapter<MostPopularAdapter.MostPopularViewHolder>() {

    inner class MostPopularViewHolder(private val binding: ItemMostPopularBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListMusicResponseItem) {
            binding.artistName.text = item.singer?.fullname ?: "Unknown Artist"
            binding.songTitle.text = item.name ?: "Unknown Song"
            Glide.with(binding.albumCover.context)
                .load(item.coverUrl)
                .into(binding.albumCover)
            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MostPopularViewHolder {
        val binding = ItemMostPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MostPopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MostPopularViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}