package com.example.tunehive.data.response

import com.google.gson.annotations.SerializedName

data class ListMusicResponse(
	@field:SerializedName("SongsResponse")
	val songsResponse: List<ListMusicResponseItem?>? = null
)

data class Singer(
	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("fullname")
	val fullname: String? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)

data class ListMusicResponseItem(
	@field:SerializedName("cover_url")
	val coverUrl: String? = null,

	@field:SerializedName("like_count")
	val likeCount: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("singer")
	val singer: Singer? = null,

	@field:SerializedName("album")
	val album: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("song_url")
	val songUrl: String? = null
)

data class Album(
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
)

