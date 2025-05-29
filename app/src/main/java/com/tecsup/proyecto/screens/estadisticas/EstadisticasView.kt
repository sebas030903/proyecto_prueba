package com.tecsup.proyecto.screens.estadisticas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.proyecto.viewmodel.AuthViewModel
import com.tecsup.proyecto.api.RetrofitClient
import com.tecsup.proyecto.model.Registro
import kotlinx.coroutines.launch

@Composable
fun EstadisticasView(authViewModel: AuthViewModel = AuthViewModel()) {
    val registros = remember { mutableStateListOf<Registro>() }
    val scope = rememberCoroutineScope()
    val usuarioActual = authViewModel.getUserName() ?: ""

    LaunchedEffect(Unit) {
        scope.launch {
            try {
                val response = RetrofitClient.api.obtenerRegistros(usuarioActual)
                registros.clear()
                registros.addAll(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Estadísticas de: $usuarioActual", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn {
            items(registros) { registro ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Tiempo de uso: ${registro.tiempo_uso_min} min")
                        Text("Parpadeos: ${registro.cantidad_parpadeos}")
                        Text("Intervalo descanso: ${registro.intervalo_descanso} min")
                    }
                }
            }
        }
    }
}