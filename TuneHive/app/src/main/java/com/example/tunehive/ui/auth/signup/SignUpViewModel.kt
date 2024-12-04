package com.example.tunehive.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.data.response.UserRequest
import com.example.tunehive.data.response.UserResponse
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _signUpResult = MutableLiveData<UserResponse>()
    val signUpResult: LiveData<UserResponse> = _signUpResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun signUp(email: String, password: String, name: String?) {
        viewModelScope.launch {
            try {
                val request = UserRequest(email, password)
                val response = ApiConfig.getApiService().signUp(request)
                _signUpResult.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An error occurred")
            }
        }
    }
}