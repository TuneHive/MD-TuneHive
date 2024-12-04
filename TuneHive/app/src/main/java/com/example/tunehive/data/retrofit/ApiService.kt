package com.example.tunehive.data.retrofit

import com.example.tunehive.data.response.UserRequest
import com.example.tunehive.data.response.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @POST("/users/")
    suspend fun signUp(@Body request: UserRequest): UserResponse

    @GET("/users/")
    suspend fun login(@Query("email") email: String, @Query("password") password: String): UserResponse
}