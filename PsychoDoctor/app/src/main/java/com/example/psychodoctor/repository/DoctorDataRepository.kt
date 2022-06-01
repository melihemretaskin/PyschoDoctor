package com.example.psychodoctor.repository

import com.example.psychodoctor.network.service.AppointmentService
import com.example.psychodoctor.util.base.BaseRepository
import javax.inject.Inject


class DoctorDataRepository @Inject constructor(private val appointmentService: AppointmentService) : BaseRepository() {
    suspend fun fetchDoctorData(
        docId : String
    ) = safeApiRequest {
        appointmentService.fetchDoctorData(docId)
    }
}