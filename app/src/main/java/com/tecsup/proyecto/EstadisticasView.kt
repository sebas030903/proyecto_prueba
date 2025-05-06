package com.tecsup.proyecto

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EstadisticasView() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Estadísticas", style = MaterialTheme.typography.displayLarge)
        Spacer(Modifier.height(16.dp))
        // Aquí podrías usar un gráfico (con librerías como MPAndroidChart o Jetpack Compose Charts)
        Text("Promedio de parpadeo diario: 16/min")
    }
}
