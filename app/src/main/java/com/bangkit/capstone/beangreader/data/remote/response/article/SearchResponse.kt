package com.bangkit.capstone.beangreader.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("results")
	val results: List<ResultsItem> = emptyList()
)

data class ResultsItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: Int,

	@field:SerializedName("title")
	val title: String? = null
)
