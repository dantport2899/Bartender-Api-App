package com.facu.bartender.domain

import com.facu.bartender.data.DataSourceRandom
import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

class ImplementacionRepoRandom(private val dataSource: DataSourceRandom): RepoRandom {
    //funcion que obtiene el trago random, retorna el datasource con la bebida random obtenida de la api
    suspend override fun ObtenListaDeTragosRandom(BebidaName:String): Resource<List<Drink>> {
        return dataSource.getBebibiaByRandom(BebidaName)
    }
}