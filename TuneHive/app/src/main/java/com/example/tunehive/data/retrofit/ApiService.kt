package com.example.tunehive.data.retrofit

import com.example.tunehive.data.response.ListMusicResponseItem
import com.example.tunehive.data.response.UserResponse
import com.example.tunehive.data.response.UserSignUp
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
interface ApiService {

    @POST("/user")
    suspend fun signUp(@Body request: UserSignUp): UserSignUp

    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String,
    ):UserResponse

    @GET("/songs/{id}")
    suspend fun getSongById(
        @Path("id") id: String
    ):ListMusicResponseItem

    @GET("/songs")
    suspend fun getAllSongs(
    ):ListMusicResponseItem

}