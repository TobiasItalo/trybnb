package com.betrybe.trybnb.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookerApiClient {
    private const val BASE_URL = "https://restful-booker.herokuapp.com/"

    val instance: BookerApiService by lazy {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(BookerApiService::class.java)
    }
}
