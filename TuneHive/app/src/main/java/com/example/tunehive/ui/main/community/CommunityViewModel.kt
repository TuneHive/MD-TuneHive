package com.example.tunehive.ui.main.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.data.response.PostResponseItem
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class CommunityViewModel : ViewModel() {

    private val _posts = MutableLiveData<List<PostResponseItem>>()
    val posts: LiveData<List<PostResponseItem>> = _posts

    // Function to fetch posts
    fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().getPosts("Bearer yourTokenHere")
                if (response.isSuccessful) {
                    _posts.postValue(response.body() ?: emptyList())
                } else {
                    // Handle error, show message
                }
            } catch (e: Exception) {
                _posts.postValue(emptyList())
            }
        }
    }
}
