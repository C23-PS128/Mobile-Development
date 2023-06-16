package com.bangkit.capstone.beangreader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    var id: Int = 0,
    var type: Int = 0,
    @PrimaryKey
    var title: String = "",
    var description: String? = "",
    var image: String? = null,
)
