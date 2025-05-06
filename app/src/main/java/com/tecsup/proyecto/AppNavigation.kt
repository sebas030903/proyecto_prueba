package com.tecsup.proyecto

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

data class NavItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navItems = listOf(
        NavItem("Inicio", Icons.Default.Home, "inicio"),
        NavItem("Estadísticas", Icons.Default.BarChart, "estadisticas"),
        NavItem("Conexión", Icons.Default.Bluetooth, "conexion"),
        NavItem("Ajustes", Icons.Default.Settings, "ajustes"),
        NavItem("Perfil", Icons.Default.Person, "perfil")
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                navItems.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "inicio",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("inicio") { InicioView(navController) }
            composable("estadisticas") { EstadisticasView() }
            composable("conexion") { ConexionView() }
            composable("ajustes") { AjustesView() }
            composable("perfil") { PerfilView() }
        }
    }
}
