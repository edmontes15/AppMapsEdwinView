package com.example.appmapsedwin.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(
    navHostController: NavHostController
){
    NavHost(navController = navHostController, startDestination = Destinations.PantallaMaps.route){
        composable(Destinations.PantallaMaps.route){
            PantallaMaps()
        }
        composable(Destinations.PantallaSettings.route){
            PantallaSettings()
        }
    }
}