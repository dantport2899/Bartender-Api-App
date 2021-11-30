package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.*
import com.facu.bartender.domain.Repo
import com.facu.bartender.vo.Resource
import kotlinx.coroutines.Dispatchers

class ByNameViewModel (private val repo:Repo):ViewModel(){

    private var bebidaData = MutableLiveData<String>()
    //establece el valor del nombre del coctel que se va a buscar
    fun setTrago(tragoName: String){
        bebidaData.value=tragoName
    }
    //valor inicial que se establece por defecto al iniciar el fragment
    init{
        setTrago("margarita")
    }

    val fetchTragosList = bebidaData.distinctUntilChanged() .switchMap {
        liveData(Dispatchers.IO){
            //se muestra el el simbolo de cargando
            emit(Resource.Loading())
            try{
                //se muestra el valor de la lista de tragos obtenidas por Nombre
                emit(repo.ObtenListaDeTragos(it))
            }catch (e:Exception){
                //se muestra un toast que dice no existe el valor del Nombre
                emit(Resource.Failure(e))
            }
        }
    }
}