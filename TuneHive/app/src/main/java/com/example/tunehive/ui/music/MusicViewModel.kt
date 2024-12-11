package com.example.tunehive.ui.music

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.data.response.ListMusicResponseItem
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    private val _musicData = MutableLiveData<ListMusicResponseItem>()
    val musicData: LiveData<ListMusicResponseItem> = _musicData

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchMusicById(musicId: String) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getSongById(musicId)
                _musicData.postValue(response)
            } catch (e: Exception) {
                _errorMessage.postValue(e.message ?: "Unknown error")
            }
        }
    }
}