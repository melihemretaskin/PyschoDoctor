package com.example.psychodoctor.ui.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psychodoctor.model.authResponse.AuthResponse
import com.example.psychodoctor.repository.AuthRepository
import com.example.psychodoctor.util.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authRepository: AuthRepository) : ViewModel() {
    var loginResponse : MutableLiveData<AuthResponse>? = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData()
    val onError : MutableLiveData<String?> = MutableLiveData()

    fun login(
        email : String,
        password : String
    ) = viewModelScope.launch {
        isLoading.value = true
        val request = authRepository.login(email,password)
        when(request){
            is NetworkResult.Success ->{
                loginResponse?.value = request.data!!
                isLoading.value = false
            }
            is NetworkResult.Error ->{
                isLoading.value = false
                onError.value = request.message
            }
        }
    }
}