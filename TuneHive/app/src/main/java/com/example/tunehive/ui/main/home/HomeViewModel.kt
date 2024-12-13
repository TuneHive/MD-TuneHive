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
    private val _filteredSongs = MutableLiveData<List<ListMusicResponseItem>>()
    val filteredSongs: LiveData<List<ListMusicResponseItem>> = _filteredSongs

    // Recent searches list
    private val _recentSearches = MutableLiveData<List<ListMusicResponseItem>>()
    val recentSearches: LiveData<List<ListMusicResponseItem>> = _recentSearches

    private val _listSongs = MutableLiveData<List<ListMusicResponseItem>>()
    val listSongs: LiveData<List<ListMusicResponseItem>> = _listSongs

    private val _listTopSongs = MutableLiveData<List<ListMusicResponseItem>>()
    val listTopSongs: LiveData<List<ListMusicResponseItem>> = _listTopSongs


    init {
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

    fun search(query: String) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().searchSong(name = query)
                _recentSearches.value = response
            } catch (e: Exception) {
                Log.e("API Error", "Failed to search songs", e)
            }
        }
    }

    // Add song to recent searches
    fun addRecentSearch(song: ListMusicResponseItem) {
        val currentList = _recentSearches.value.orEmpty()
        if (!currentList.contains(song)) {
            val updatedList = listOf(song) + currentList
            _recentSearches.value = updatedList.take(5) // Limit to 5 recent searches
        }
    }
}

