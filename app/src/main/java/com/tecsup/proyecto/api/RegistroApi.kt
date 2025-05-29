package com.tecsup.proyecto.api

import com.tecsup.proyecto.model.Registro
import retrofit2.http.GET
import retrofit2.http.Query

interface RegistroApi {
    @GET("api/registro/")
    suspend fun obtenerRegistros(@Query("usuario") usuario: String): List<Registro>
}