package com.tecsup.proyecto

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Inicio : BottomNavItem("inicio", Icons.Default.Home, "Inicio")
    object Estadisticas : BottomNavItem("estadisticas", Icons.Default.BarChart, "Estadísticas")
    object Conexion : BottomNavItem("conexion", Icons.Default.Bluetooth, "Conexión")
    object Ajustes : BottomNavItem("ajustes", Icons.Default.Settings, "Ajustes")
    object Perfil : BottomNavItem("perfil", Icons.Default.Person, "Perfil")

    companion object {
        val items = listOf(Inicio, Estadisticas, Conexion, Ajustes, Perfil)
    }
}