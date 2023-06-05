package com.bangkit.capstone.beangreader.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class DetailCoffeesResponse(

    @field:SerializedName("result")
	val result: DetailItem,

    @field:SerializedName("error")
	val error: Boolean? = null,

    @field:SerializedName("message")
	val message: String? = null
)

data class DetailItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String? = null
)
