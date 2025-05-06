package com.tecsup.proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConexionView() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Conexión", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(16.dp))
        Text("Estado: Desconectado")
        Spacer(Modifier.height(8.dp))
        Button(onClick = { }) {
            Text("Reconectar")
        }
    }
}
