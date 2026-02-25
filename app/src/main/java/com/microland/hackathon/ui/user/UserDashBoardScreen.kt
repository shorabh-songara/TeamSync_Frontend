package com.microland.hackathon.ui.user

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.microland.hackathon.navigation.Routes
import com.microland.hackathon.ui.screens.HomeScreen
import com.microland.hackathon.ui.screens.MyProjectScreen
import com.microland.hackathon.ui.screens.ProfileScreen

@Composable
fun MainScreen(navController: NavController) {

    val bottomNavController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(bottomNavController)
        }
    ) { paddingValues ->

        NavHost(
            navController = bottomNavController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(paddingValues)
        ) {

            composable(Routes.HOME) {
                HomeScreen(rootNavController = navController)
            }

            composable(Routes.MY_PROJECTS) {
                MyProjectScreen()
            }

            composable(Routes.PROFILE) {
                ProfileScreen(rootNavController = navController)
            }
        }
    }
}