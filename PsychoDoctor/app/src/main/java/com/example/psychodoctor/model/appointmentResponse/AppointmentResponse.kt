package com.example.psychodoctor.model.appointmentResponse


import com.google.gson.annotations.SerializedName

data class AppointmentResponse(
    @SerializedName("reservation")
    val reservation: List<Reservation>?,
    @SerializedName("success")
    val success: Int?
)