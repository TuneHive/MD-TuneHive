package com.example.tunehive.data.response

import com.google.gson.annotations.SerializedName

data class PostResponse(

	@field:SerializedName("PostResponse")
	val postResponse: List<PostResponseItem?>? = null
)

data class PostResponseItem(

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("content")
	val content: String? = null
)

data class User(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class PostRequestBody(
	val content: String,
	val username: String,
	val user_id: Int
)
