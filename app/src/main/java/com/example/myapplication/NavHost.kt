package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MainNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home" ) {
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("search") {
            SearchScreen(navController = navController)
        }

        composable("shipment") {
            ShipmentScreen(navController = navController)
        }

        composable("calculator") {
            CalculateScreen(navController = navController)
        }

        composable("success") {
            SuccessScreen()
        }
    }
}