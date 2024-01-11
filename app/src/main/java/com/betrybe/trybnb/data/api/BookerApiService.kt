package com.betrybe.trybnb.data.api

import com.betrybe.trybnb.data.models.BookingAuthBody
import com.betrybe.trybnb.data.models.BookingById
import com.betrybe.trybnb.data.models.BookingIds
import com.betrybe.trybnb.data.models.BookingToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BookerApiService {
    @GET("booking")
    suspend fun getBookingIds(): Response<List<BookingIds>>

    @GET("booking/{id}")
    suspend fun getBookingById(
        @Header("Accept") accept: String,
        @Path("id") id: Int,
    ): Response<BookingById>

    @POST("auth")
    suspend fun postAuth(
        @Body bookingAuthBody: BookingAuthBody,
    ): Response<BookingToken>
}
