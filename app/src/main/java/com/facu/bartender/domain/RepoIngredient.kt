package com.facu.bartender.domain

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

interface RepoIngredient {
    //funcion suspendida que trabaja por corutinas que obtiene la lista de los tragos por ID
    suspend fun ObtenListaDeTragosIngredient(BebidaName:String): Resource<List<Drink>>
}