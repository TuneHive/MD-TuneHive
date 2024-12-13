package com.example.tunehive.data.response

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("CommentResponse")
	val commentResponse: List<CommentResponseItem?>? = null
)


data class CommentResponseItem(

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("content")
	val content: String? = null
)
