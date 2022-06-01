package com.example.psychodoctor.model.docDataResponse


import com.google.gson.annotations.SerializedName

data class Doctor(
    @SerializedName("name")
    val name: String?,
    @SerializedName("photo_url")
    val photoUrl: String?,
    @SerializedName("profession")
    val profession: String?,
    @SerializedName("work_place")
    val workPlace: Any?
)