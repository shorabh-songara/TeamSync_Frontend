package com.microland.hackathon.repository

import android.content.Context
import androidx.activity.contextaware.ContextAware
import com.microland.hackathon.data.TokenManager
import com.microland.hackathon.data.models.CreateProjectRequest
import com.microland.hackathon.data.models.CreateProjectResponse
import com.microland.hackathon.network.RetrofitInstance
import kotlinx.coroutines.flow.first

class ProjectRepository(context: Context) {

    private val api = RetrofitInstance.getApi(context)
    private val tokenManager = TokenManager(context)

    suspend fun createProject(
        title: String,
        description: String,
        hackathonName: String,
        category: String,
        requiredSkills: List<String>,
        teamSize: Int
    ): CreateProjectResponse {

        val token = tokenManager.getToken.first()

        if (token.isNullOrEmpty()) {
            throw Exception("User not authenticated")
        }

        return api.createProject(
            "Bearer $token",
            CreateProjectRequest(
                title,
                description,
                hackathonName,
                category,
                requiredSkills,
                teamSize
            )
        )
    }
}