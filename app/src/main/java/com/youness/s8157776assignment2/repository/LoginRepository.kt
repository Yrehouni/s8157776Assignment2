package com.youness.s8157776assignment2.repository

import com.youness.s8157776assignment2.api.RetrofitClient
import com.youness.s8157776assignment2.model.LoginRequest
import com.youness.s8157776assignment2.model.LoginResponse

class LoginRepository {

    suspend fun login(username: String, password: String): LoginResponse {
        val request = LoginRequest(username, password)
        return RetrofitClient.api.login(request)
    }
}
