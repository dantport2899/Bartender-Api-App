package com.facu.bartender.vo
//estados en los que se encuentra la app
sealed class Resource<out T> {
    //estado cargando: mmuestra el simbolo de cargando
    class Loading<out T>: Resource<T>()
    //estado success: muestra el valor de la lista de cocteles que se busco dependendiendo de caso
    data class Success<out T>(val data: T) : Resource<T>()
    //estado failure: muestra un toast con el error que dio el programa
    data class Failure<out T>(val exception: Exception) :Resource<T>()
}