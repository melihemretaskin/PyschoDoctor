package com.example.psychodoctor.repository

import com.example.psychodoctor.network.service.AppointmentService
import com.example.psychodoctor.util.base.BaseRepository
import javax.inject.Inject


class AuthRepository @Inject constructor(private val appointmentService: AppointmentService) : BaseRepository() {

    suspend fun login(
        docId: String,
        password: String
    ) = safeApiRequest {
        appointmentService.login(docId,password)
    }
}