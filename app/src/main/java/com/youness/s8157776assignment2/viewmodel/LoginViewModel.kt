package com.youness.s8157776assignment2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youness.s8157776assignment2.model.LoginRequest
import com.youness.s8157776assignment2.model.LoginResponse
import com.youness.s8157776assignment2.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    val loginResult = MutableLiveData<LoginResponse?>()
    val errorMessage = MutableLiveData<String>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)

                Log.d("LOGIN_RESPONSE", "keypass = ${response.keypass}")

                loginResult.value = response
            } catch (e: Exception) {
                errorMessage.value = "Login failed: ${e.message}"
            }
        }
    }
}
