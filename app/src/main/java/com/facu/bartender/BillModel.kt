package com.facu.bartender
//establece el modelo de los datos para el bill
data class BillModel (
    var date: String? = null,
    var pricebill: String? = null,
    var pricetip: String? = null,
    var totalprice: String? = null
)