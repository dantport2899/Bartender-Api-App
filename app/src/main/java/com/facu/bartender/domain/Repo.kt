package com.facu.bartender.domain

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

interface Repo {
    //funcion suspendida que trabaja por corutinas que obtiene la lista de los tragos por nombre
    suspend fun ObtenListaDeTragos(BebidaName:String):Resource<List<Drink>>
}