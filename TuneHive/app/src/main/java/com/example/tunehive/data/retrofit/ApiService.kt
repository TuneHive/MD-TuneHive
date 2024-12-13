package com.example.tunehive.data.retrofit

import com.example.tunehive.data.response.CommentResponseItem
import com.example.tunehive.data.response.ListMusicResponse
import com.example.tunehive.data.response.ListMusicResponseItem
import com.example.tunehive.data.response.PostRequestBody
import com.example.tunehive.data.response.PostResponse
import com.example.tunehive.data.response.PostResponseItem
import com.example.tunehive.data.response.UserDetails
import com.example.tunehive.data.response.UserResponse
import com.example.tunehive.data.response.UserSignUp
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @GET("/recommend")
    suspend fun getRecommendedSongs(
        @Header("Authorization") token: String
    ): ListMusicResponse

    @GET("/posts")
    suspend fun getPosts(
        @Header("Authorization") token: String
    ): Response<List<PostResponseItem>>

    @GET("/posts/{post_id}")
    suspend fun getPostById(
        @Path("post_id") postId: Int
    ): PostResponseItem

    @GET("/posts/{post_id}/comments")
    suspend fun getComments(
        @Path("post_id") postId: Int
    ): List<CommentResponseItem>

    @POST("/posts/{post_id}/comments")
    suspend fun createComment(
        @Path("post_id") postId: Int,
        @Body comment: CommentResponseItem
    ): Response<CommentResponseItem>

    @PUT("/posts/{post_id}/like")
    suspend fun likePost(
        @Path("post_id") postId: Int
    ): PostResponseItem

    @POST("/posts")
    suspend fun createPost(
        @Body postRequestBody: PostRequestBody,
        @Query("user_id") userId: Int
    ): Response<PostResponseItem>

}