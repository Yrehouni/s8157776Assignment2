package com.youness.s8157776assignment2.repository

import com.youness.s8157776assignment2.api.ApiService
import com.youness.s8157776assignment2.model.LoginRequest
import com.youness.s8157776assignment2.model.LoginResponse
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun login(username: String, password: String): LoginResponse {
        return api.login(LoginRequest(username, password))
    }
}