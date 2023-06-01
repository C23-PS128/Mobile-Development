package com.bangkit.capstone.beangreader.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var image: String? = null,
)
