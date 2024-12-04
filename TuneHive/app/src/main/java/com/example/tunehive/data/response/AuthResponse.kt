package com.example.tunehive.data.response


//sesuaikan dengan api nanti
data class UserRequest(
    val email: String,
    val password: String
)

data class UserResponse(
    val id: String,
    val email: String,
    val message: String? = null,
    val error: Boolean
)