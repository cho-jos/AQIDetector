package com.example.aqidetector.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqidetector.repository.AqiRepository
import com.example.aqidetector.repository.AqiRepositoryInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AqiViewModel(
    private val repository: AqiRepositoryInterface = AqiRepository()
) : ViewModel() {
    // asynchronous flow of values that should be observed
    private val _aqiReport = MutableStateFlow<AqiReportModel?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow<Boolean>(false)

    val aqiReport: StateFlow<AqiReportModel?> get() = _aqiReport.asStateFlow()
    val errorMessage: StateFlow<String?> get() = _errorMessage.asStateFlow()
    val isLoading: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    fun getAqiReportHere() {
        // ViewModel classes have their own scope, they can run
        // suspended functions in their own concurrency unit
        viewModelScope.launch {
            _isLoading.value = true
            val response = repository.getAqiHere()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Log.d("Success", "$body?.size")
                    _isLoading.value = false
                    _aqiReport.value = body
                }
            } else {
                val error = response.errorBody()
                if (error != null) {
                    Log.d("AQI Report Error", error.string())
                    _isLoading.value = false
                    _errorMessage.value = error.string()
                }
            }
        }
    }
}