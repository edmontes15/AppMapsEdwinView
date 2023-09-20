package com.example.appmapsedwin.ui.theme

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    val navigationItems = listOf(
        Destinations.PantallaMaps,
        Destinations.PantallaSettings
    )
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems)}
    ){
        NavigationHost(navController)
    }
}