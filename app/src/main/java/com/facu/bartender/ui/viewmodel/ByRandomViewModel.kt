package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.*
import com.facu.bartender.domain.RepoRandom
import com.facu.bartender.vo.Resource
import kotlinx.coroutines.Dispatchers

class ByRandomViewModel (private val repo: RepoRandom): ViewModel(){

    private var RandomData = MutableLiveData<String>()
    //establece el valor del random que se va a buscar
    fun setTragoRandom(tragoRandom: String){
        RandomData.value=tragoRandom
    }
    //valor inicial que se establece por defecto al iniciar el fragment
    init{
        setTragoRandom("")
    }

    val fetchRandomList = RandomData.distinctUntilChanged() .switchMap {
        liveData(Dispatchers.IO){
            //se muestra el el simbolo de cargando
            emit(Resource.Loading())
            try{
                //se muestra el valor de la lista de tragos obtenidas por random
                emit(repo.ObtenListaDeTragosRandom(it))
            }catch (e:Exception){
                //se muestra un toast que dice no existe el valor
                emit(Resource.Failure(e))
            }
        }
    }
}