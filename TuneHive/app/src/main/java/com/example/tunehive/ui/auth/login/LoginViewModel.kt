package com.example.tunehive.ui.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.data.response.UserResponse
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class LoginViewModel:ViewModel() {
    private val _loginResult = MutableLiveData<UserResponse>()
    val loginResult: LiveData<UserResponse> = _loginResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = ApiConfig.getApiService().login(email, password)
                _loginResult.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An error occurred")
            }
        }
    }
}