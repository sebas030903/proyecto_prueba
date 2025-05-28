package com.tecsup.proyecto.screens.conexion

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConexionView() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Conexión") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text("Aquí podrás gestionar dispositivos conectados como sensores Bluetooth.", style = MaterialTheme.typography.bodyLarge)
            // Más adelante puedes agregar listas de dispositivos o escaneo BLE
        }
    }
}