package com.example.psychodoctor.repository

import com.example.psychodoctor.network.service.AppointmentService
import com.example.psychodoctor.util.base.BaseRepository
import javax.inject.Inject


class AppointmentRepository @Inject constructor(private val appointmentService: AppointmentService) : BaseRepository() {
     suspend fun getAppointments(
         docId : String
     ) = safeApiRequest {
         appointmentService.getAppointments(docId)
     }

    suspend fun confirmAppointment(
        resId : String,
        approval : String
    ) = safeApiRequest {
        appointmentService.confirmAppointment(resId,approval)
    }

    suspend fun declineAppointment(
        resId : String,
        approval : String
    ) = safeApiRequest {
        appointmentService.declineAppointment(resId,approval)
    }
}