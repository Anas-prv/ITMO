package com.hw.hw2

import retrofit2.http.GET
import retrofit2.http.Query

interface Hw2Api {
    @GET("v1/gifs/trending")
    suspend fun getTrendingGifs(
        @Query("api_key") apiKey: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Hw2Response
}
