package com.example.psychodoctor.model.appointmentResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Reservation(
    @SerializedName("approval")
    val approval: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("reservation_date")
    val reservationDate: String?,
    @SerializedName("res_id")
    val resId: String?
): Parcelable