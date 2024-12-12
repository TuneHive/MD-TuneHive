package com.example.tunehive.ui.music

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.tunehive.R
import com.example.tunehive.databinding.ActivityMusicBinding
import com.example.tunehive.ui.main.MainActivity

class MusicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMusicBinding
    private val viewModel: MusicViewModel by viewModels()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve music ID passed in the intent
        val musicId = intent.getStringExtra(EXTRA_MUSIC_ID) ?: return

        setupObservers()
        viewModel.fetchMusicById(musicId)

        binding.btnPlay.setOnClickListener {
            togglePlayPause()
        }
        binding.btnBack.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun togglePlayPause() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.pause()
                binding.btnPlay.setImageResource(R.drawable.baseline_play_circle_24) // Play button
            } else {
                it.start()
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_circle_24) // Pause button
            }
        }
    }

    private fun setupMediaPlayer(songUrl: String?) {
        if (songUrl.isNullOrEmpty()) {
            Toast.makeText(this, "Song URL is invalid", Toast.LENGTH_SHORT).show()
            return
        }

        // Release the existing MediaPlayer before creating a new one to prevent memory leaks
        mediaPlayer?.release()

        mediaPlayer = MediaPlayer().apply {
            setDataSource(songUrl)
            setOnPreparedListener {
                Toast.makeText(this@MusicActivity, "Ready to play!", Toast.LENGTH_SHORT).show()
                start()
                binding.btnPlay.setImageResource(R.drawable.baseline_pause_circle_24) // Set as Pause button
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
                // Update UI with the music data
                binding.songTitle.text = it.name ?: "Unknown Title"
                binding.artistName.text = it.singer?.username ?: "Unknown Artist"

                Glide.with(this)
                    .load(it.coverUrl)
                    .placeholder(R.drawable.sample_album_cover)
                    .into(binding.albumArt)

                // Setup MediaPlayer with song URL
                setupMediaPlayer(it.songUrl)
            }
        }

        // Handle errors
        viewModel.errorMessage.observe(this) { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        }
    }

    // Ensure that media player is properly released when the activity is destroyed
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release() // Clean up media player
        mediaPlayer = null
    }

    // Release media player when activity is paused or stopped
    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
    }

    companion object {
        const val EXTRA_MUSIC_ID = "extra_music_id" // Use this key consistently
    }
}