package com.example.artbook.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by AralBenli on 25.04.2023.
 */

@Entity(tableName = "art")
data class ArtModel(
    var name: String,
    var artistName: String,
    var year: Int,
    var imageUrl: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)


