package com.example.tunehive.ui.music

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tunehive.R
import com.example.tunehive.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicBinding
    private val viewModel: MusicViewModel by viewModels()
    private var mediaPlayer: MediaPlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val musicId = intent.getStringExtra(EXTRA_MUSIC_ID) ?: return

        setupObservers()
        viewModel.fetchMusicById(musicId)

        binding.btnPlay.setOnClickListener {
            togglePlayPause()
        }
    }

    private fun togglePlayPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                binding.btnPlay.setImageResource(R.drawable.baseline_play_circle_24)
            } else {
                it.start()
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_circle_24)
            }
        }
    }

    private fun setupMediaPlayer(songUrl: String?) {
        if (songUrl.isNullOrEmpty()) {
            Toast.makeText(this, "Song URL is invalid", Toast.LENGTH_SHORT).show()
            return
        }

        mediaPlayer = MediaPlayer().apply {
            setDataSource(songUrl)
            setOnPreparedListener {
                Toast.makeText(this@MusicActivity, "Ready to play!", Toast.LENGTH_SHORT).show()
            }
            setOnErrorListener { _, _, _ ->
                Toast.makeText(this@MusicActivity, "Error playing song", Toast.LENGTH_SHORT).show()
                true
            }
            prepareAsync()
        }
    }

    private fun setupObservers() {
        viewModel.musicData.observe(this) { music ->
            music?.let {
                binding.songTitle.text = it.name ?: "Unknown Title"
                binding.artistName.text = it.singer?.username ?: "Unknown Album"

                Glide.with(this)
                    .load(it.coverUrl)
                    .placeholder(R.drawable.sample_album_cover)
                    .into(binding.albumArt)

                setupMediaPlayer(it.songUrl)

            }
        }

        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }


    companion object{
        const val EXTRA_MUSIC_ID = "extra_music_id"
    }
}