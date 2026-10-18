package com.youness.s8157776assignment2.repository

import com.youness.s8157776assignment2.api.RetrofitClient
import com.youness.s8157776assignment2.model.DashboardResponse

class DashboardRepository {

    suspend fun getDashboard(keypass: String): DashboardResponse {
        return RetrofitClient.api.getDashboard(keypass)
    }
}