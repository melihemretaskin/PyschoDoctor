package com.example.psychodoctor.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psychodoctor.model.appointmentResponse.AppointmentResponse
import com.example.psychodoctor.model.docDataResponse.DoctorDataResponse
import com.example.psychodoctor.repository.AppointmentRepository
import com.example.psychodoctor.repository.DoctorDataRepository
import com.example.psychodoctor.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appointmentRepository: AppointmentRepository,
    private val doctorDataRepository: DoctorDataRepository
) : ViewModel() {
    //Appointment Response
    var appointmentResponse : MutableLiveData<AppointmentResponse> = MutableLiveData()
    val appointmentResponseIsLoading : MutableLiveData<Boolean> = MutableLiveData()
    val appointmentResponseError : MutableLiveData<String?> = MutableLiveData()

    fun getAppointments(
        docId : String
    )  = viewModelScope.launch {
        appointmentResponseIsLoading.value = true
        val request = appointmentRepository.getAppointments(docId)
        when(request){
           is  NetworkResult.Success ->{
                appointmentResponseIsLoading.value = false
                appointmentResponse.value = request.data!!
            }

            is NetworkResult.Error ->{
                appointmentResponseIsLoading.value = false
                appointmentResponseError.value = request.message
            }
        }
    }
}