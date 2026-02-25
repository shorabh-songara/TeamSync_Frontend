package com.microland.hackathon.ui.user


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.microland.hackathon.navigation.Routes

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem(
        route = Routes.HOME,
        icon = Icons.Default.Home,
        label = "Home"
    )

    object MyProjects : BottomNavItem(
        route = Routes.MY_PROJECTS,
        icon = Icons.Default.Work,
        label = "Projects"
    )

    object Profile : BottomNavItem(
        route = Routes.PROFILE,
        icon = Icons.Default.Person,
        label = "Profile"
    )
}