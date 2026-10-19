package com.youness.s8157776assignment2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.youness.s8157776assignment2.api.ApiService
import com.youness.s8157776assignment2.model.DashboardResponse
import com.youness.s8157776assignment2.model.LoginRequest
import com.youness.s8157776assignment2.model.LoginResponse
import com.youness.s8157776assignment2.repository.LoginRepository
import com.youness.s8157776assignment2.viewmodel.LoginViewModel
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class LoginViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Test
    fun `successful login publishes response and sends credentials`() = runTest {
        val fakeApi = FakeApiService()
        val viewModel = LoginViewModel(LoginRepository(fakeApi))

        viewModel.login("12345678", "Youness")
        advanceUntilIdle()

        assertEquals(LoginResponse("travel"), viewModel.loginResult.value)
        assertEquals(LoginRequest("12345678", "Youness"), fakeApi.loginRequest)
    }

    @Test
    fun `failed login publishes error message`() = runTest {
        val fakeApi = FakeApiService(loginError = IOException("No connection"))
        val viewModel = LoginViewModel(LoginRepository(fakeApi))

        viewModel.login("12345678", "Youness")
        advanceUntilIdle()

        assertEquals("Login failed: No connection", viewModel.errorMessage.value)
    }

    private class FakeApiService(
        private val loginError: Exception? = null
    ) : ApiService {

        var loginRequest: LoginRequest? = null

        override suspend fun login(request: LoginRequest): LoginResponse {
            loginRequest = request
            loginError?.let { throw it }
            return LoginResponse("travel")
        }

        override suspend fun getDashboard(keypass: String): DashboardResponse {
            error("getDashboard should not be called in a login test")
        }
    }
}