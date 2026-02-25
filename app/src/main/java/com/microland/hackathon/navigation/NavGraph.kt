package com.microland.hackathon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.microland.hackathon.ui.auth.LoginScreen
import com.microland.hackathon.ui.auth.RegisterScreen
import com.microland.hackathon.ui.screens.BrowseProjectScreen
import com.microland.hackathon.ui.screens.CreateProfileScreen
import com.microland.hackathon.ui.screens.CreateProjectScreen
import com.microland.hackathon.ui.screens.HomeScreen
import com.microland.hackathon.ui.screens.SplashScreen
import com.microland.hackathon.ui.user.MainScreen


@Composable
fun NavGraph(navController: NavHostController){
    NavHost(
        navController=navController,
        startDestination = Routes.SPLASH
    ){
        composable(Routes.SPLASH) {
            SplashScreen(navController)
        }

        composable(Routes.LOGIN) {
            LoginScreen(navController)
        }

        composable(Routes.MAIN) {
            MainScreen(navController)
        }

        composable(Routes.REGISTER){
            RegisterScreen(navController)
        }

        composable(Routes.CREATE_PROFILE){
            CreateProfileScreen(navController)
        }

        composable(Routes.CREATE_PROJECT){
            CreateProjectScreen(navController)
        }

        composable(Routes.BROWSE){
            BrowseProjectScreen(navController)
        }

    }
}