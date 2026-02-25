package com.microland.hackathon.data.models

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class AuthResponse(
    val access_token: String,
    val token_type: String
)

data class ProfileCreateRequest(
    val name: String,
    val college: String,
    val bio: String,
    val skills: List<String>,
    val experience_level: String,
    val preferred_role: String
)

data class UserResponse(
    val id: Int,
    val name: String,
    val email: String,
    val profile: Profile?
)

data class Profile(
    val bio: String?,
    val skills: String?,
    val college: String?,
    val year: String?,
    val major: String?,
    val mobile_number: String?,
    val role: String?
)
data class ProfileResponse(
    val message: String,
    val profile: ProfileData
)

data class ProfileData(
    val user_id: Int,
    val name: String,
    val college: String,
    val bio: String,
    val skills: List<String>,
    val experience_level: String,
    val preferred_role: String
)

data class CreateProjectRequest(
    val title: String,
    val description: String,
    val hackathon_name: String,
    val category: String,
    val required_skills: List<String>,
    val team_size: Int
)

data class CreateProjectResponse(
    val message: String,
    val project: ProjectData
)

data class ProjectData(
    val id: Int,
    val title: String,
    val description: String,
    val hackathon_name: String,
    val category: String,
    val required_skills: List<String>,
    val team_size: Int,
    val current_members: Int,
    val creator_id: Int
)