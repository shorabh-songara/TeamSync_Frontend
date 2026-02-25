package com.microland.hackathon.ViewModel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microland.hackathon.data.models.CreateProjectResponse
import com.microland.hackathon.repository.ProjectRepository
import kotlinx.coroutines.launch

class ProjectViewModel(context: Context) : ViewModel() {

    private val repository = ProjectRepository(context)

    var createState by mutableStateOf<CreateProjectResponse?>(null)
    var error by mutableStateOf<String?>(null)
    var isLoading by mutableStateOf(false)

    fun createProject(
        title: String,
        description: String,
        hackathonName: String,
        category: String,
        requiredSkills: List<String>,
        teamSize: Int
    ) {
        viewModelScope.launch{
            try {
                isLoading = true
                error = null

                createState = repository.createProject(
                    title,
                    description,
                    hackathonName,
                    category,
                    requiredSkills,
                    teamSize
                )

            } catch (e: Exception) {
                error = e.message
            } finally {
                isLoading = false
            }
        }
    }
}