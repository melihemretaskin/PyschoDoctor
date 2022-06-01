package com.example.psychodoctor.model.authResponse


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Data(
    @SerializedName("doc_id")
    val docId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("photo_url")
    val photoUrl: String?,
    @SerializedName("profession")
    val profession: String?,
    @SerializedName("workplace")
    val workplace: String?
) : Parcelable