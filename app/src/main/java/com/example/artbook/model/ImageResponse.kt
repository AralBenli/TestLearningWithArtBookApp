package com.example.artbook.model

import com.google.gson.annotations.SerializedName


data class ImageResponse(
    val hits: List<ImageResultItem>,
    val total: Int,
    val totalHits: Int
)


data class ImageResultItem(
    val collections: Int,
    val comments: Int,
    val downloads: Int,
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val tags: String,
    val type: String,
    val user: String,
    val userImageURL: String,
    @SerializedName("user_id")
    val userId: Int,
    val views: Int,
)