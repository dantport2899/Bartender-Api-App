package com.facu.bartender.vo

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientRandom {
//se establecen los valores iniciales del webserviceRandom
    val WebServiceRandom by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(com.facu.bartender.domain.WebServiceRandom::class.java)
    }
}