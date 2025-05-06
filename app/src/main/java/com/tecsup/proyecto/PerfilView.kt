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

    val usuarios by viewModel.allUsers.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(24.dp)
    ) {
        Text("Perfil del Usuario", style = MaterialTheme.typography.displayLarge)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nombre")
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Ingrese su nombre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Correo")
        TextField(
            value = correo,
            onValueChange = { correo = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            label = { Text("Ingrese su correo") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Edad")
        TextField(
            value = edad,
            onValueChange = { edad = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Ingrese su edad") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val edadInt = edad.toIntOrNull()
            if (nombre.isNotEmpty() && correo.isNotEmpty() && edadInt != null) {
                val user = User(nombre = nombre, correo = correo, edad = edadInt)
                viewModel.saveOrUpdateUser(user)
                nombre = ""
                correo = ""
                edad = ""
                usuarioSeleccionado = null
            }
        }) {
            Text("Guardar Usuario")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Usuarios Guardados: ${usuarios.size}", style = MaterialTheme.typography.titleLarge)
        usuarios.forEach { user ->
            Button(
                onClick = { usuarioSeleccionado = user },
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            ) {
                Text("Seleccionar: ${user.nombre}")
            }
        }

        usuarioSeleccionado?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Perfil Seleccionado:")
            Text("Nombre: ${it.nombre}")
            Text("Correo: ${it.correo}")
            Text("Edad: ${it.edad}")

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.deleteUser(it)
                    usuarioSeleccionado = null
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar Usuario")
            }
        }
    }
}
