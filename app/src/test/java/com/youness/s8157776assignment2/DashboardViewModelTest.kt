package com.youness.s8157776assignment2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.youness.s8157776assignment2.api.ApiService
import com.youness.s8157776assignment2.model.DashboardResponse
import com.youness.s8157776assignment2.model.Entity
import com.youness.s8157776assignment2.model.LoginRequest
import com.youness.s8157776assignment2.model.LoginResponse
import com.youness.s8157776assignment2.repository.DashboardRepository
import com.youness.s8157776assignment2.viewmodel.DashboardViewModel
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class DashboardViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `successful load publishes dashboard and uses keypass`() = runTest {
        val entity = Entity(
            destination = "Sydney",
            country = "Australia",
            bestSeason = "Summer",
            popularAttraction = "Opera House",
            description = "A harbour city"
        )
        val response = DashboardResponse(entities = listOf(entity), entityTotal = 1)
        val fakeApi = FakeApiService(dashboardResponse = response)
        val viewModel = DashboardViewModel(DashboardRepository(fakeApi))

        viewModel.loadDashboard("travel")
        advanceUntilIdle()

        assertEquals(response, viewModel.dashboardData.value)
        assertEquals("travel", fakeApi.requestedKeypass)
    }

    @Test
    fun `failed load publishes error message`() = runTest {
        val fakeApi = FakeApiService(
            dashboardError = IOException("No connection")
        )
        val viewModel = DashboardViewModel(DashboardRepository(fakeApi))

        viewModel.loadDashboard("travel")
        advanceUntilIdle()

        assertEquals("Failed to load data: No connection", viewModel.errorMessage.value)
    }

    private class FakeApiService(
        private val dashboardResponse: DashboardResponse = DashboardResponse(emptyList(), 0),
        private val dashboardError: Exception? = null
    ) : ApiService {

        var requestedKeypass: String? = null

        override suspend fun login(request: LoginRequest): LoginResponse {
            error("login should not be called in a dashboard test")
        }

        override suspend fun getDashboard(keypass: String): DashboardResponse {
            requestedKeypass = keypass
            dashboardError?.let { throw it }
            return dashboardResponse
        }
    }
}