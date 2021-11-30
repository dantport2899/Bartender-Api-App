package com.facu.bartender.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
// data class para la api, serialized name es el nombre del dato igual al del JSOn que obtiene retrofit
@Parcelize
data class Drink(
    @SerializedName("idDrink")
    val IDCotelBebida: String = "",
    @SerializedName("strDrinkThumb")
    val imagen: String = "",
    @SerializedName("strDrink")
    val nombre: String = "",
    @SerializedName("strInstructions")
    val descripcion: String = "",
    @SerializedName("strIngredient1")
    val ingrediente1: String = "",
    @SerializedName("strIngredient2")
    val ingrediente2: String = "",
    @SerializedName("strIngredient3")
    val ingrediente3: String = "",
    @SerializedName("strIngredient4")
    val ingrediente4: String = "",
    @SerializedName("strIngredient5")
    val ingrediente5: String = "",
    @SerializedName("strIngredient6")
    val ingrediente6: String = "",
    @SerializedName("strIngredient7")
    val ingrediente7: String = "",
    @SerializedName("strIngredient8")
    val ingrediente8: String = "",
    @SerializedName("strIngredient9")
    val ingrediente9: String = "",
    @SerializedName("strIngredient10")
    val ingrediente10: String = "",
    @SerializedName("strMeasure1")
    val Measure1: String = "",
    @SerializedName("strMeasure2")
    val Measure2: String = "",
    @SerializedName("strMeasure3")
    val Measure3: String = "",
    @SerializedName("strMeasure4")
    val Measure4: String = "",
    @SerializedName("strMeasure5")
    val Measure5: String = "",
    @SerializedName("strMeasure6")
    val Measure6: String = "",
    @SerializedName("strMeasure7")
    val Measure7: String = "",
    @SerializedName("strMeasure8")
    val Measure8: String = "",
    @SerializedName("strMeasure9")
    val Measure9: String = "",
    @SerializedName("strMeasure10")
    val Measure10: String = ""

):Parcelable
// lista de bebidas con los datos superiores
data class coctelesList(
    @SerializedName("drinks")
    val coctelesList: List<Drink>)