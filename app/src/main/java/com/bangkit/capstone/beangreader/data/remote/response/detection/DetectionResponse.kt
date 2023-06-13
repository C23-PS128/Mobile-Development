package com.bangkit.capstone.beangreader.data.remote.response.detection

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetectionResponse(

	@field:SerializedName("pesan_kualitas")
	val pesanKualitas: String? = null,

	@field:SerializedName("user_image")
	val userImage: String? = null,

	@field:SerializedName("labels")
	val labels: String? = null
) : Parcelable
