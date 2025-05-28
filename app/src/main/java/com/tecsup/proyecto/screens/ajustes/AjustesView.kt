package com.tecsup.proyecto.screens.ajustes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjustesView() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ajustes") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text("Aquí podrás personalizar la app: temas, notificaciones, etc.", style = MaterialTheme.typography.bodyLarge)
            // Más adelante puedes agregar Switches, Sliders, etc.
        }
    }
}