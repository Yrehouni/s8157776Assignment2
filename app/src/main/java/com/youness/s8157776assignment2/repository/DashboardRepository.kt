package com.youness.s8157776assignment2.repository

import com.youness.s8157776assignment2.api.ApiService
import com.youness.s8157776assignment2.model.DashboardResponse
import javax.inject.Inject

class DashboardRepository @Inject constructor(
    private val api: ApiService
) {
    suspend fun getDashboard(keypass: String): DashboardResponse {
        return api.getDashboard(keypass)
    }
}