package com.tecsup.proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EstadisticasView() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Estadísticas", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(16.dp))

        // Datos simulados
        val parpadeosHoy = 230
        val promedioParpadeos = 16
        val duracionUso = "2h 15min"
        val objetivo = 18

        Text("Parpadeos hoy: $parpadeosHoy")
        Spacer(Modifier.height(8.dp))
        Text("Promedio actual: $promedioParpadeos/min")
        Spacer(Modifier.height(8.dp))
        Text("Duración de uso: $duracionUso")
        Spacer(Modifier.height(8.dp))
        Text("Objetivo: $objetivo parpadeos/min")
        Spacer(Modifier.height(8.dp))

        Text(
            text = when {
                promedioParpadeos >= objetivo -> "✅ Buen ritmo de parpadeo"
                promedioParpadeos >= 15 -> "⚠️ Ritmo aceptable, pero puede mejorar"
                else -> "❗ Nivel bajo de parpadeo. Cuida tu vista"
            },
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(16.dp))
        Text("Historial semanal:", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        Text("Lunes: 15/min")
        Text("Martes: 16/min")
        Text("Miércoles: 14/min")
        Text("Jueves: 17/min")
        Text("Viernes: 13/min")
    }
}

