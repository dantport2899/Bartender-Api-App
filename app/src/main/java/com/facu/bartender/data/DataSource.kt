package com.facu.bartender.data

import com.facu.bartender.data.model.Drink
import com.facu.bartender.vo.Resource
import com.facu.bartender.vo.RetrofitClient
//fuente de los datos de los cocteles por nombre de coctel
class DataSource {

    suspend fun getBebibiaByName(coctelName: String): Resource<List<Drink>>{
        //obtiene los datos de la api a traves de retrofit y dependiendo del webservice utilizado en este caso webservice
        val resultRequest = RetrofitClient.webservice.obtenerByName(coctelName).coctelesList
        resultRequest?.let{
            return Resource.Success(it)
        }
        //en caso que siga siendo igual a null se retorna un advertencia que no existe ninguna bebida con ese nombre
        var e= Exception("No existe cocteles con ese nombre!!")
        return Resource.Failure(e)


    }
}
