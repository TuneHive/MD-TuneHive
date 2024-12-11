package com.example.tunehive.data.response

data class ListMusicResponse(
	val listMusicResponse: List<ListMusicResponseItem?>? = null
)

data class Singer(
	val id: Int? = null,
	val fullname: String? = null,
	val email: String? = null,
	val username: String? = null
)

data class ListMusicResponseItem(
	val coverUrl: String? = null,
	val likeCount: Int? = null,
	val updatedAt: String? = null,
	val singer: Singer? = null,
	val album: Album? = null,
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null,
	val songUrl: String? = null
)

data class Album(
	val name: String? = null,
	val createdAt: String? = null,
	val id: Int? = null
)

