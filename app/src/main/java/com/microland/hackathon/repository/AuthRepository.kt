package com.microland.hackathon.repository

import android.content.Context
import com.microland.hackathon.data.models.*
import com.microland.hackathon.network.RetrofitInstance

class AuthRepository(context: Context) {

    private val api = RetrofitInstance.getApi(context)

    suspend fun login(email: String, password: String): AuthResponse {
        return api.login(LoginRequest(email, password))
    }

    suspend fun register(name: String, email: String, password: String): AuthResponse {
        return api.register(RegisterRequest(name, email, password))
    }

    suspend fun createProfile(
        token: String,
        name: String,
        college: String,
        bio: String,
        skills: List<String>,
        experience: String,
        role: String
    ): ProfileResponse {

        return api.createProfile(
            "Bearer $token",
            ProfileCreateRequest(
                name,
                college,
                bio,
                skills,
                experience,
                role
            )
        )
    }
}