package com.bangkit.capstone.beangreader.data.remote.response.article

import com.google.gson.annotations.SerializedName

data class TypeCoffeeResponse(

	@field:SerializedName("result")
	val result: List<TypeCoffeeItem> = emptyList(),

	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class TypeCoffeeItem(

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("type")
	val type: Int? = null,

	@field:SerializedName("title")
	val title: String? = null
)
