package com.facu.bartender.domain

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

interface RepoRandom {
    //funcion suspendida que trabaja por corutinas que obtiene la lista del trago Random
    suspend fun ObtenListaDeTragosRandom(BebidaName:String): Resource<List<Drink>>
}