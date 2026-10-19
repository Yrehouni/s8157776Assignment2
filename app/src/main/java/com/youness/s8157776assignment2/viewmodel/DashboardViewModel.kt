package com.youness.s8157776assignment2.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youness.s8157776assignment2.model.DashboardResponse
import com.youness.s8157776assignment2.repository.DashboardRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {



    val dashboardData = MutableLiveData<DashboardResponse>()
    val errorMessage = MutableLiveData<String>()

    fun loadDashboard(keypass: String) {
        viewModelScope.launch {
            try {
                val response = repository.getDashboard(keypass)
                dashboardData.value = response
            } catch (e: Exception) {
                errorMessage.value = "Failed to load data: ${e.message}"
            }
        }
    }
}