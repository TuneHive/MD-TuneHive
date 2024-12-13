package com.example.tunehive.ui.main.community

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.data.response.PostRequestBody
import com.example.tunehive.data.response.PostResponseItem
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Response

class PostViewModel : ViewModel() {

    private val _postCreationResponse = MutableLiveData<Response<PostResponseItem>?>()
    val postCreationResponse: LiveData<Response<PostResponseItem>?> = _postCreationResponse

    // Function to create a post
    fun createPost(postText: String, userName: String, userId: Int) {
        if (userId <= 0) {
            // Log or handle the invalid ID scenario gracefully
            _postCreationResponse.postValue(null)
            return
        }

        val postRequestBody = PostRequestBody(content = postText, username = userName, user_id = userId)

        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().createPost(postRequestBody, userId)
                _postCreationResponse.postValue(response)
            } catch (e: Exception) {
                _postCreationResponse.postValue(null)
                e.printStackTrace()
            }
        }
    }
}