package com.example.psychodoctor.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psychodoctor.repository.AppointmentRepository
import com.example.psychodoctor.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppointmentDetailViewModel @Inject constructor(private val appointmentRepository: AppointmentRepository): ViewModel() {
    var result : MutableLiveData<String> = MutableLiveData()
    var onError : MutableLiveData<String> = MutableLiveData()

    fun confirmAppointment(
        resId : String,
        approval : String
    ) = viewModelScope.launch {
        val request = appointmentRepository.confirmAppointment(resId,approval)
        when(request){
            is NetworkResult.Success ->{
                result.value = "Appointment Confirmed."
            }
            is NetworkResult.Error ->{
                onError.value = "Something went wrong."
            }
        }
    }

    fun declineAppointment(
        resId : String,
        approval : String
    ) = viewModelScope.launch {
        val request = appointmentRepository.confirmAppointment(resId,approval)
        when(request){
            is NetworkResult.Success ->{
                result.value = "Appointment Declined.."
            }
            is NetworkResult.Error ->{
                onError.value = "Something went wrong."
            }
        }
    }
}