package com.tecsup.proyecto.screens.perfil

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tecsup.proyecto.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PerfilView(authViewModel: AuthViewModel) {
    val user = FirebaseAuth.getInstance().currentUser

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Perfil de Usuario", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            user?.displayName?.let {
                Text(text = "Nombre: $it")
            }
            Text(text = "Correo: ${user?.email ?: "No disponible"}")

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { authViewModel.logout() },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Cerrar sesión", color = MaterialTheme.colorScheme.onError)
            }
        }
    }
}