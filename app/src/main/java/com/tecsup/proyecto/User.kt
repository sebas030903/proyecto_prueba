package com.tecsup.proyecto

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,  // ID generado automáticamente
    val nombre: String,
    val correo: String,
    val edad: Int
)
