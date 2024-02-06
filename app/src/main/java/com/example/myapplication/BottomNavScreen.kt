package com.example.myapplication

sealed class Screens(val route : String) {
    object Home : Screens("home_route")
    object Calculate : Screens("calculate_route")
    object Shipment : Screens("shipment_route")
    object Profile : Screens("profile_route")
}