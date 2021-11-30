package com.facu.bartender.domain

import com.facu.bartender.data.model.coctelesList
import retrofit2.http.GET
import retrofit2.http.Query

//interface webservice suspendida que obtiene los datos Random
interface WebServiceRandom {
    @GET("random.php")
    suspend fun obtenerByName(@Query("i") BebidaName: String): coctelesList
}