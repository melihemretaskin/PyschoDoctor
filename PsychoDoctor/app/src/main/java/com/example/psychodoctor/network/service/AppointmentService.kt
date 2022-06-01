package com.example.psychodoctor.network.service

import com.example.psychodoctor.model.appointmentResponse.AppointmentResponse
import com.example.psychodoctor.model.authResponse.AuthResponse
import com.example.psychodoctor.model.docDataResponse.DoctorDataResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface AppointmentService {
    @FormUrlEncoded
    @POST("/doctors/fetch_doctor_data.php")
    suspend fun fetchDoctorData(
        @Field("doc_id") docId : String
    ) : DoctorDataResponse

    @FormUrlEncoded
    @POST("/doctors/login.php")
    suspend fun login(
        @Field("doc_id") docId: String,
        @Field("password") password: String
    ) : AuthResponse


    @FormUrlEncoded
    @POST("/doctors/reservation_check.php")
    suspend fun getAppointments(
        @Field("doc_id") docId : String
    ) : AppointmentResponse

    @FormUrlEncoded
    @POST("/doctors/appointment_confirmation.php")
    suspend fun confirmAppointment(
        @Field("res_id") resId : String,
        @Field("approval") approval : String
    )

    @FormUrlEncoded
    @POST("/doctors/appointment_refusal.php")
    suspend fun declineAppointment(
        @Field("res_id") resId : String,
        @Field("approval") approval : String
    )
}