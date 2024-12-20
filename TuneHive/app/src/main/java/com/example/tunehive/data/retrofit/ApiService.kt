package com.example.tunehive.data.retrofit

import com.example.tunehive.data.response.ListMusicResponse
import com.example.tunehive.data.response.ListMusicResponseItem
import com.example.tunehive.data.response.UserDetails
import com.example.tunehive.data.response.UserResponse
import com.example.tunehive.data.response.UserSignUp
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/user")
    suspend fun signUp(@Body request: UserSignUp): UserSignUp

    @FormUrlEncoded
    @POST("/login")
    suspend fun login(
        @Field("username") email: String,
        @Field("password") password: String,
    ):UserResponse

    @GET("/users/details")
    suspend fun getUser(
        @Header("Authorization") token: String
    ):UserDetails

    @GET("/songs/{id}")
    suspend fun getSongById(
        @Path("id") id: String
    ):ListMusicResponseItem

    @GET("/songs/?page=1&itemPerPage=20")
    suspend fun getAllSongs(
    ):List<ListMusicResponseItem>

    @GET("/songs/?page=1&itemPerPage=20&top=true")
    suspend fun getTopSongs(
    ):List<ListMusicResponseItem>

    @GET("/songs/recommendations")
    suspend fun getRecommendedSongs(
        @Header("Authorization") token: String
    ):List<ListMusicResponseItem>

    @GET("songs/")
    suspend fun searchSong(
        @Query("name") name: String,
        @Query("page") page: Int = 1,
        @Query("itemPerPage") itemPerPage: Int = 20
    ): List<ListMusicResponseItem>

}