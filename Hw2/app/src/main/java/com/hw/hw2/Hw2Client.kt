package com.hw.hw2
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Hw2Client {
    private const val BASE_URL = "https://api.giphy.com/"

    val hw2Api: Hw2Api by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Hw2Api::class.java)
    }
}
