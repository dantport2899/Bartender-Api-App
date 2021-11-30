package com.facu.bartender.data

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource
import com.facu.bartender.vo.RetrofitClientRandom
//fuente de los datos de los cocteles random
class DataSourceRandom {
    suspend fun getBebibiaByRandom(coctelName: String): Resource<List<Drink>> {
        //obtiene los datos de la api a traves de retrofit y dependiendo del webservice utilizado en este caso webserviceRandom
        val resultRequest=RetrofitClientRandom.WebServiceRandom.obtenerByName(coctelName).coctelesList
        //si el valor es diferent a null retorna su contenido que es la lista de lista de bebida
        resultRequest?.let{
            return Resource.Success(it)
        }
        //en caso que siga siendo igual a null se retorna un advertencia que no existe ninguna bebida
        var e= Exception("No existe cocteles con ese nombre!!")
        return Resource.Failure(e)


    }
}