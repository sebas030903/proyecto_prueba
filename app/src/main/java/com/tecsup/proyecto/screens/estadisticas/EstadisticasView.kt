package com.tecsup.proyecto.screens.estadisticas

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EstadisticasView() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Estadísticas") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
        ) {
            Text("Aquí irán las estadísticas de parpadeo, uso, etc.", style = MaterialTheme.typography.bodyLarge)
            // Puedes agregar gráficos, contadores, etc.
        }
    }
}