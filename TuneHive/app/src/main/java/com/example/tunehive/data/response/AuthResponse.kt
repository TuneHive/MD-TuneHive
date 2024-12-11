package com.example.tunehive.data.response



data class UserResponse(
    val access_token: String,
    val token_type: String
)

data class UserSignUp(
    val email: String? = null,
    val password: String? = null,
    val phone: String? = "0000",
    val description: String? = "",
    val fullname: String? = email,
    val username: String? = ""
)

data class UserDetails(
val updatedAt: String? = null,
val phone: String? = null,
val followingCount: Int? = null,
val createdAt: String? = null,
val description: String? = null,
val id: Int? = null,
val fullname: String? = null,
val followerCount: Int? = null,
val email: String? = null,
val username: String? = null
)


