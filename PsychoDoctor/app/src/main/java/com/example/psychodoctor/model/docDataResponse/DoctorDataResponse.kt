package com.example.psychodoctor.model.docDataResponse


import com.google.gson.annotations.SerializedName

data class DoctorDataResponse(
    @SerializedName("doc")
    val doctor: Doctor?,
    @SerializedName("error")
    val error: Boolean?
)