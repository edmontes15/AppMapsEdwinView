package com.example.appmapsedwin.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items:List<Destinations>
){
    val currentRoute = currenRoute(navController)
    BottomNavigation(){
        items.forEach { screen->
            BottomNavigationItem(
                selected = currentRoute==screen.route,
                onClick = {
                          navController.navigate(screen.route){
                              popUpTo(navController.graph.findStartDestination().id){
                                  saveState=true
                              }
                              launchSingleTop=true
                          }
                },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.name ) })
        }
    }
}

@Composable
private fun currenRoute(navController: NavHostController): String?{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

