package com.tecsup.proyecto

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AjustesView() {
    var alertasActivas by remember { mutableStateOf(true) }
    var intervaloRecordatorio by remember { mutableStateOf("10 minutos") }

    val intervalos = listOf("5 minutos", "10 minutos", "15 minutos")

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Ajustes", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(16.dp))

        // Switch para activar/desactivar alertas
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            Text("Alertas activadas", modifier = Modifier.weight(1f))
            Switch(checked = alertasActivas, onCheckedChange = {
                alertasActivas = it
            })
        }

        Spacer(Modifier.height(16.dp))

        // Dropdown para seleccionar intervalo de parpadeo
        Text("Intervalo de recordatorio")
        var expanded by remember { mutableStateOf(false) }

        Box {
            OutlinedButton(onClick = { expanded = true }) {
                Text(intervaloRecordatorio)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                intervalos.forEach { intervalo ->
                    DropdownMenuItem(
                        text = { Text(intervalo) },
                        onClick = {
                            intervaloRecordatorio = intervalo
                            expanded = false
                        }
                    )
                }
            }
        }

        Spacer(Modifier.height(24.dp))

        Text("Versión: 1.0.0", style = MaterialTheme.typography.bodySmall)
        Text("Desarrollado por: TuNombre", style = MaterialTheme.typography.bodySmall)
    }
}
