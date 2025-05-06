package com.tecsup.proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConexionView() {
    var estadoConexion by remember { mutableStateOf("Desconectado") }
    var nombreDispositivo by remember { mutableStateOf("Ninguno") }
    var reconectarHabilitado by remember { mutableStateOf(true) }

    // Simulación de conexión/desconexión
    fun conectar() {
        // Simulación de intento de conexión
        estadoConexion = "Conectado"
        nombreDispositivo = "Blink Device 001"
        reconectarHabilitado = false // Deshabilitamos el botón al estar conectado
    }

    fun desconectar() {
        // Simulación de desconexión
        estadoConexion = "Desconectado"
        nombreDispositivo = "Ninguno"
        reconectarHabilitado = true // Habilitamos el botón para reconectar
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Conexión", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(16.dp))

        // Estado de la conexión
        Text("Estado de la conexión: $estadoConexion")

        if (estadoConexion == "Conectado") {
            Text("Dispositivo conectado: $nombreDispositivo")
        }

        Spacer(Modifier.height(16.dp))

        // Botón para reconectar o desconectar
        if (estadoConexion == "Conectado") {
            Button(onClick = { desconectar() }) {
                Text("Desconectar")
            }
        } else {
            Button(onClick = { conectar() }, enabled = reconectarHabilitado) {
                Text("Reconectar")
            }
        }
    }
}

