package com.example.artbook.di

import com.example.artbook.util.Constants
import com.example.artbook.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by AralBenli on 25.04.2023.
 */
interface ApiService {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q")searchQuery: String ,
        @Query("key")apiKey : String = Constants.API_KEY
    ):Response<ImageResponse>



}