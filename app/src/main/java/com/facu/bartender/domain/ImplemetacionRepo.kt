package com.facu.bartender.domain

import com.facu.bartender.data.DataSource
import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource

class ImplemetacionRepo(private val dataSource: DataSource): Repo {
    //funcion que obtiene la lista de tragos por nombre, retorna el datasource con las bebida por nombre obtenida de la api
    suspend override fun ObtenListaDeTragos(BebidaName:String): Resource<List<Drink>> {
        return dataSource.getBebibiaByName(BebidaName)
    }
}