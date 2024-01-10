package com.betrybe.trybnb.data.models

import com.google.gson.annotations.SerializedName

data class BookingById(
    @SerializedName("firstname")
    val firstName: String,

    @SerializedName("lastname")
    val lastName: String,

    @SerializedName("totalprice")
    val totalPrice: Double,

    @SerializedName("depositpaid")
    val depositPaid: Boolean,

    @SerializedName("bookingdates")
    val bookingDates: BookingDates,

    @SerializedName("additionalneeds")
    val additionalNeeds: String,
)

