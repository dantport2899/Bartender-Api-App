package com.facu.bartender.domain

import com.facu.bartender.data.DataSourceIngredient
import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

class ImplementacionRepoIngredient(private val dataSource: DataSourceIngredient): RepoIngredient {
    //funcion que obtiene la lista de tragos por iD, retorna el datasource con las bebida por id obtenida de la api
    suspend override fun ObtenListaDeTragosIngredient(BebidaName:String): Resource<List<Drink>> {
        return dataSource.getBebibiaByIngredient(BebidaName)
    }
}