package com.example.appmapsedwin.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val icon: ImageVector,
    val name: String
){
    object PantallaMaps: Destinations("pantalla1",Icons.Rounded.Home, "Maps")
    object PantallaSettings: Destinations("pantalla2",Icons.Rounded.Settings, "Settings")
}
