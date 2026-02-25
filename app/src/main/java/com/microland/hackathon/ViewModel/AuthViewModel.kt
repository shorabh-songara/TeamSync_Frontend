package com.microland.hackathon.ViewModel

import android.content.Context
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microland.hackathon.data.TokenManager
import com.microland.hackathon.data.models.AuthResponse
import com.microland.hackathon.data.models.ProfileResponse
import com.microland.hackathon.repository.AuthRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {

    private val repository = AuthRepository(context)
    private val tokenManager = TokenManager(context)

    var loginState by mutableStateOf<AuthResponse?>(null)
    var loginError by mutableStateOf<String?>(null)

    var registerState by mutableStateOf<AuthResponse?>(null)
    var registerError by mutableStateOf<String?>(null)

    var profileState by mutableStateOf<ProfileResponse?>(null)
    var profileError by mutableStateOf<String?>(null)

    var isLoading by mutableStateOf(false)

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                loginError = null

                val response = repository.login(email, password)
                loginState = response

                tokenManager.saveToken(response.access_token)

            } catch (e: Exception) {
                loginError = e.message ?: "Login failed"
            } finally {
                isLoading = false
            }
        }
    }

    fun register(name: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                isLoading = true
                registerError = null

                val response = repository.register(name, email, password)
                registerState = response

                tokenManager.saveToken(response.access_token)

            } catch (e: Exception) {
                registerError = e.message ?: "Registration failed"
            } finally {
                isLoading = false
            }
        }
    }

    fun createProfile(
        name: String,
        college: String,
        bio: String,
        skills: List<String>,
        experience: String,
        role: String
    ) {
        viewModelScope.launch {
            try {
                isLoading = true
                profileError = null

                val token = tokenManager.getToken.first()

                if (token.isNullOrEmpty()) {
                    profileError = "User not authenticated"
                    return@launch
                }

                profileState = repository.createProfile(
                    token,
                    name,
                    college,
                    bio,
                    skills,
                    experience,
                    role
                )

            } catch (e: Exception) {
                profileError = e.message ?: "Profile creation failed"
            } finally {
                isLoading = false
            }
        }
    }

    fun clearLoginState() {
        loginState = null
        loginError = null
    }

    fun clearRegisterState() {
        registerState = null
        registerError = null
    }
}