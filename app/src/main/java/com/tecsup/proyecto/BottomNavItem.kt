package com.tecsup.proyecto

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Representa los elementos de la barra de navegación inferior.
 * Solo se muestran cuando el usuario ha iniciado sesión.
 */
sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val title: String
) {
    object Inicio : BottomNavItem("inicio", Icons.Default.Home, "Inicio")
    object Estadisticas : BottomNavItem("estadisticas", Icons.Default.BarChart, "Estadísticas")
    object Conexion : BottomNavItem("conexion", Icons.Default.Bluetooth, "Conexión")
    object Ajustes : BottomNavItem("ajustes", Icons.Default.Settings, "Ajustes")
    object Perfil : BottomNavItem("perfil", Icons.Default.Person, "Perfil")

    companion object {
        /**
         * Lista de ítems que deben aparecer en la barra de navegación inferior.
         */
        val items = listOf(Inicio, Estadisticas, Conexion, Ajustes, Perfil)
    }
}