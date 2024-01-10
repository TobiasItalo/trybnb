package com.betrybe.trybnb.data.models

import com.google.gson.annotations.SerializedName

data class BookingIds(
    @SerializedName("bookingid")
    val bookingId: Int,
)
