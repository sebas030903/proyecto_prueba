package com.tecsup.proyecto

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PerfilView(viewModel: UserViewModel = viewModel()) {
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var usuarioSeleccionado by remember { mutableStateOf<User?>(null) }

    // Recolectar usuarios usando collectAsState
    val usuarios by viewModel.allUsers.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        // Título
        Text("Perfil del Usuario", style = MaterialTheme.typography.displayLarge)

        // Formulario para ingresar usuario
        Spacer(modifier = Modifier.height(16.dp))

        // Nombre
        Text("Nombre")
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("Ingrese su nombre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Correo
        Text("Correo")
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            label = { Text("Ingrese su correo") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Edad
        Text("Edad")
        TextField(
            value = edad,
            onValueChange = { edad = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            label = { Text("Ingrese su edad") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de guardar
        Button(onClick = {
            // Validar edad (debe ser un número)
            val edadInt = edad.toIntOrNull()
            if (nombre.isNotEmpty() && correo.isNotEmpty() && edadInt != null) {
                val nuevoUsuario = User(
                    nombre = nombre,
                    correo = correo,
                    edad = edadInt
                )
                viewModel.insert(nuevoUsuario)
                // Limpiar campos
                nombre = ""
                correo = ""
                edad = ""
            } else {
                // Mostrar mensaje de error si faltan campos o la edad es inválida
                // Puedes mostrar un Toast o Snackbar aquí
            }
        }) {
            Text("Guardar Usuario")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Mostrar lista de usuarios guardados
        Text("Usuarios Guardados", style = MaterialTheme.typography.titleLarge)
        usuarios.forEach { user ->
            Button(
                onClick = {
                    usuarioSeleccionado = user
                },
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                Text("Cambiar a: ${user.nombre}")
            }
        }

        // Mostrar datos del usuario seleccionado
        usuarioSeleccionado?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Perfil Seleccionado: ${it.nombre}")
            Text("Correo: ${it.correo}")
            Text("Edad: ${it.edad}")
        }
    }
}
