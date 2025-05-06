package com.tecsup.proyecto

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*

@Composable
fun AppNavigation(navController: NavHostController, userViewModel: UserViewModel) {
    val items = listOf(
        BottomNavItem.Inicio,
        BottomNavItem.Estadisticas,
        BottomNavItem.Conexion,
        BottomNavItem.Ajustes,
        BottomNavItem.Perfil
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                items.forEach { item ->
                    NavigationBarItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Inicio.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Inicio.route) { InicioView(navController) }
            composable(BottomNavItem.Estadisticas.route) { EstadisticasView() }
            composable(BottomNavItem.Conexion.route) { ConexionView() }
            composable(BottomNavItem.Ajustes.route) { AjustesView() }
            composable(BottomNavItem.Perfil.route) { PerfilView(viewModel = userViewModel) }
        }
    }
}
