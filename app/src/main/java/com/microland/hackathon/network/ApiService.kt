package com.microland.hackathon.network

import com.microland.hackathon.data.models.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/register")
    suspend fun register(
        @Body request: RegisterRequest
    ): AuthResponse

    @POST("auth/login")
    suspend fun login(
        @Body request: LoginRequest
    ): AuthResponse
    @POST("auth/profile")
    suspend fun createProfile(
        @Header("Authorization") token: String,
        @Body request: ProfileCreateRequest
    ): ProfileResponse

    @GET("auth/me")
    suspend fun getMe(): UserResponse


    @POST("projects/")
    suspend fun createProject(
        @Header("Authorization") token: String,
        @Body request: CreateProjectRequest
    ): CreateProjectResponse
}