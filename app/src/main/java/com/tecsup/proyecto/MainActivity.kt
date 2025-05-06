package com.tecsup.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.tecsup.proyecto.ui.theme.ProyectoTheme

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el ViewModel directamente usando el ViewModelProvider
        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        setContent {
            ProyectoTheme {
                // Usamos directamente AppNavigation aquí
                val navController = rememberNavController()

                AppNavigation(navController = navController, userViewModel = userViewModel)
            }
        }
    }
}


