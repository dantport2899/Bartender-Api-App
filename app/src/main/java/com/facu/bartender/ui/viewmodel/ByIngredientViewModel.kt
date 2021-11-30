package com.facu.bartender.ui.viewmodel

import androidx.lifecycle.*
import com.facu.bartender.domain.RepoIngredient
import com.facu.bartender.vo.Resource
import kotlinx.coroutines.Dispatchers

class ByIngredientViewModel (private val repo: RepoIngredient): ViewModel(){

    private var IngredientData = MutableLiveData<String>()
    //establece el valor del ID del coctel que se va a buscar
    fun setTragoIngredient(tragoIngredient: String){
        IngredientData.value=tragoIngredient
    }
    //valor inicial que se establece por defecto al iniciar el fragment
    init{
        setTragoIngredient("11001")
    }

    val fetchIngredientList = IngredientData.distinctUntilChanged() .switchMap {
        liveData(Dispatchers.IO){
            //se muestra el el simbolo de cargando
            emit(Resource.Loading())
            try{
                //se muestra el valor de la lista de tragos obtenidas por ID
                emit(repo.ObtenListaDeTragosIngredient(it))
            }catch (e:Exception){
                //se muestra un toast que dice no existe el valor del ID
                emit(Resource.Failure(e))
            }
        }
    }
}