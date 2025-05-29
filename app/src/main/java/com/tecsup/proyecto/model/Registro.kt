package com.tecsup.proyecto.model

data class Registro(
    val id: Int,
    val usuario: String,
    val tiempo_uso_min: Int,
    val cantidad_parpadeos: Int,
    val intervalo_descanso: Int
)