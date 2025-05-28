package com.tecsup.proyecto

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.tecsup.proyecto.screens.ajustes.AjustesView
import com.tecsup.proyecto.screens.conexion.ConexionView
import com.tecsup.proyecto.screens.estadisticas.EstadisticasView
import com.tecsup.proyecto.screens.inicio.InicioScreen
import com.tecsup.proyecto.screens.login.LoginScreen
import com.tecsup.proyecto.screens.perfil.PerfilView
import com.tecsup.proyecto.viewmodel.AuthViewModel

@Composable
fun AppNavigation(
    authViewModel: AuthViewModel = viewModel() // ✅ ViewModel correctamente inyectado
) {
    val navController = rememberNavController()
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()

    val bottomItems = BottomNavItem.items // usa el companion object

    Scaffold(
        bottomBar = {
            if (isAuthenticated) {
                NavigationBar {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentRoute = navBackStackEntry?.destination?.route

                    bottomItems.forEach { item ->
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
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = if (isAuthenticated) BottomNavItem.Inicio.route else "login",
            modifier = Modifier.padding(innerPadding)
        ) {
            // Login screen (solo si no está autenticado)
            composable("login") {
                LoginScreen(
                    authViewModel = authViewModel,
                    onLoginSuccess = {
                        navController.navigate(BottomNavItem.Inicio.route) {
                            popUpTo("login") { inclusive = true }
                        }
                    }
                )
            }

            // Pantallas protegidas
            composable(BottomNavItem.Inicio.route) {
                InicioScreen(navController)
            }
            composable(BottomNavItem.Estadisticas.route) {
                EstadisticasView()
            }
            composable(BottomNavItem.Conexion.route) {
                ConexionView()
            }
            composable(BottomNavItem.Ajustes.route) {
                AjustesView()
            }
            composable(BottomNavItem.Perfil.route) {
                PerfilView(authViewModel = authViewModel)
            }
        }
    }
}