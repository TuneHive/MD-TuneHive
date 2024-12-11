package com.example.tunehive.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TokenViewModel:ViewModel() {
    val accessToken = MutableLiveData<String>()
}