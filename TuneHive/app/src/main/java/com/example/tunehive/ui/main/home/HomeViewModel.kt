package com.example.tunehive.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tunehive.R

class HomeViewModel : ViewModel() {

    // Sample song list (you can replace this with data from a repository or API)
    private val _songs = MutableLiveData<List<Song>>().apply {
        value = listOf(
            Song("Song One", "Artist A", R.drawable.sample_album_cover),
            Song("Song Two", "Artist B", R.drawable.sample_album_cover),
            Song("Another Song", "Artist A", R.drawable.sample_album_cover),
            Song("Melody One", "Artist C", R.drawable.sample_album_cover),
        )
    }
    val songs: LiveData<List<Song>> = _songs

    // Filtered list based on search query
    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongs: LiveData<List<Song>> = _filteredSongs

    // Recent searches list
    private val _recentSearches = MutableLiveData<List<Song>>()
    val recentSearches: LiveData<List<Song>> = _recentSearches

    init {
        // Initialize with all songs
        _filteredSongs.value = _songs.value
    }

    // Function to perform search
    fun search(query: String) {
        _filteredSongs.value = if (query.isEmpty()) {
            _songs.value
        } else {
            _songs.value?.filter {
                it.name.contains(query, ignoreCase = true) || it.artist.contains(query, ignoreCase = true)
            }
        }
    }

    // Add song to recent searches
    fun addRecentSearch(song: Song) {
        val currentList = _recentSearches.value.orEmpty()
        if (!currentList.contains(song)) {
            // Add the song to the front of the list
            val updatedList = listOf(song) + currentList
            _recentSearches.value = updatedList.take(5)  // Limit to 5 recent searches
        }
    }

}

