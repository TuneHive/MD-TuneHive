package com.example.tunehive.ui.auth.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tunehive.data.response.UserSignUp
import com.example.tunehive.data.retrofit.ApiConfig
import kotlinx.coroutines.launch

class SignUpViewModel : ViewModel() {

    private val _signUpResult = MutableLiveData<UserSignUp>()
    val signUpResult: LiveData<UserSignUp> = _signUpResult

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun signUp(email: String, password: String) {
        viewModelScope.launch {
            try {
                val request = UserSignUp(email, password)
                val response = ApiConfig.getApiService().signUp(request)
                _signUpResult.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message ?: "An error occurred")
            }
        }
    }
}