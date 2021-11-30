package com.facu.bartender.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facu.bartender.DrinkModel
import com.facu.bartender.R
import com.facu.bartender.SQLiteHelper
import com.facu.bartender.data.model.Drink
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_descripcion_bebidas.*

class DescripcionBebidasFrag : Fragment() {

    private lateinit var drink: Drink
    private lateinit var price: String

    private lateinit var sqLiteHelper: SQLiteHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         requireArguments().let{
            drink = it.getParcelable("drink")!!
        }
        sqLiteHelper= SQLiteHelper(requireContext())
    }
    //funcion que agrega las bebidas a la base de datos de sqlite
    private fun addDrink() {
        val id= drink.IDCotelBebida.toInt()
        val name= drink.nombre
        val price= price

        if (name.isEmpty()|| price.isEmpty()){
            Toast.makeText(requireContext(), "Please enter price", Toast.LENGTH_SHORT).show()
        }else{
            val std = DrinkModel(id=id,name=name, price=price)
            val status = sqLiteHelper.insertDrink(std)
            if(status>-1){
                Toast.makeText(requireContext(),"Drink Added....", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(requireContext(),"Drink not saved", Toast.LENGTH_SHORT).show()
            }
        }
    }
    //al crearse la view se infla el fragmento fragment_descripcion_bebidas
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_descripcion_bebidas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        IDbebida.text=drink.IDCotelBebida
        Picasso.get().load(drink.imagen).into(tragoimg)
        Cocteltxt.text=drink.nombre
        Detailtxt.text=drink.descripcion
        ingrediente1.text=drink.ingrediente1
        ingrediente2.text=drink.ingrediente2
        ingrediente3.text=drink.ingrediente3
        ingrediente4.text=drink.ingrediente4
        ingrediente5.text=drink.ingrediente5
        ingrediente6.text=drink.ingrediente6
        ingrediente7.text=drink.ingrediente7
        ingrediente8.text=drink.ingrediente8
        ingrediente9.text=drink.ingrediente9
        ingrediente10.text=drink.ingrediente10
        medida1.text=drink.Measure1
        medida2.text=drink.Measure2
        medida3.text=drink.Measure3
        medida4.text=drink.Measure4
        medida5.text=drink.Measure5
        medida6.text=drink.Measure6
        medida7.text=drink.Measure7
        medida8.text=drink.Measure8
        medida9.text=drink.Measure9
        medida10.text=drink.Measure10

        AddToMenu.setOnClickListener{
            price= CoctelPrice.text.toString()
            addDrink()
        }
    }
}