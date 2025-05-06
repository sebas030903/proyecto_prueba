package com.tecsup.proyecto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tecsup.proyecto.ui.theme.ProyectoTheme

class MainActivity : ComponentActivity() {

    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inicializa el ViewModel con su Factory
        val factory = UserViewModelFactory(application)
        userViewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        setContent {
            ProyectoTheme {
                // Puedes pasar el ViewModel a tus vistas
                MainApp(userViewModel)
            }
        }
    }
}

