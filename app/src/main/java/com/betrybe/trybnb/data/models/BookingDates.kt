package com.betrybe.trybnb.data.models

import com.google.gson.annotations.SerializedName

data class BookingDates(
    @SerializedName("checkin")
    val checkIn: String,

    @SerializedName("checkout")
    val checkOut: String,
)
