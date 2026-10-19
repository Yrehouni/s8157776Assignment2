package com.youness.s8157776assignment2.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.youness.s8157776assignment2.model.LoginResponse
import com.youness.s8157776assignment2.repository.LoginRepository
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {


    val loginResult = MutableLiveData<LoginResponse?>()
    val errorMessage = MutableLiveData<String>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(username, password)


                loginResult.value = response
            } catch (e: Exception) {
                errorMessage.value = "Login failed: ${e.message}"
            }
        }
    }
}
