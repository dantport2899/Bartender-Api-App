package com.facu.bartender.data

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource
import com.facu.bartender.vo.RetrofitClientIngredient
//fuente de los datos de los cocteles por ID de coctel
class DataSourceIngredient {

    suspend fun getBebibiaByIngredient(coctelName: String): Resource<List<Drink>> {
        //obtiene los datos de la api a traves de retrofit y dependiendo del webservice utilizado en este caso webserviceIngredient
        val resultRequest = RetrofitClientIngredient.WebServiceIngredient.obtenerByName(coctelName).coctelesList
        //si el valor es diferent a null retorna su contenido que es la lista de lista de bebida
        resultRequest?.let{
            return Resource.Success(it)
        }
        //en caso que siga siendo igual a null se retorna un advertencia que no existe ninguna bebida con ese id
        var e= Exception("No existe cocteles con ese ID!!")
        return Resource.Failure(e)


    }
}