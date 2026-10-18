package com.youness.s8157776assignment2.api

import com.youness.s8157776assignment2.model.DashboardResponse
import com.youness.s8157776assignment2.model.Entity
import com.youness.s8157776assignment2.model.LoginRequest
import com.youness.s8157776assignment2.model.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("footscray/auth")
    suspend fun login(
        @Body request: LoginRequest
    ): LoginResponse

    @GET("dashboard/{keypass}")
    suspend fun getDashboard(
        @Path("keypass") keypass: String
    ): DashboardResponse
}