package com.example.tunehive.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.R
import com.example.tunehive.data.response.ListMusicResponseItem
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    // Sample song list (you can replace this with data from a repository or API)
    private val _songs = MutableLiveData<List<Song>>().apply {
        value = listOf(
        )
    }
    val songs: LiveData<List<Song>> = _songs

    // Filtered list based on search query
    private val _filteredSongs = MutableLiveData<List<Song>>()
    val filteredSongs: LiveData<List<Song>> = _filteredSongs

    // Recent searches list
    private val _recentSearches = MutableLiveData<List<Song>>()
    val recentSearches: LiveData<List<Song>> = _recentSearches

    private val _listSongs = MutableLiveData<List<ListMusicResponseItem>>()
    val listSongs: LiveData<List<ListMusicResponseItem>> = _listSongs

    private val _listTopSongs = MutableLiveData<List<ListMusicResponseItem>>()
    val listTopSongs: LiveData<List<ListMusicResponseItem>> = _listTopSongs


    init {
        // Initialize with all songs
        _filteredSongs.value = _songs.value
        fetchAllSongs()
        fetchTopSongs()
    }

    private fun fetchAllSongs() {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getAllSongs()
                Log.d("API Response", response.toString()) // Debug response content

                _listSongs.value = response
            } catch (e: Exception) {
                Log.e("API Error", "Failed to fetch songs", e)
                e.printStackTrace()
            }
        }
    }

    private fun fetchTopSongs() {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getTopSongs()
                Log.d("API Response", response.toString()) // Debug response content

                _listTopSongs.value = response
            } catch (e: Exception) {
                Log.e("API Error", "Failed to fetch songs", e)
                e.printStackTrace()
            }
        }
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

