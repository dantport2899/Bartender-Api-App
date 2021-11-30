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
import kotlinx.android.synthetic.main.fragment_descripcion_by_ingredient.*

class DescripcionByIngredientFrag : Fragment() {

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
        return inflater.inflate(R.layout.fragment_descripcion_by_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        IDCoctel.text=drink.IDCotelBebida
        Picasso.get().load(drink.imagen).into(tragoimgByIngredient)
        CocteltxtByIngredient.text=drink.nombre
        DetailtxtByIngredient.text=drink.descripcion
        ingrediente1ByIngredient.text=drink.ingrediente1
        ingrediente2ByIngredient.text=drink.ingrediente2
        ingrediente3ByIngredient.text=drink.ingrediente3
        ingrediente4ByIngredient.text=drink.ingrediente4
        ingrediente5ByIngredient.text=drink.ingrediente5
        ingrediente6ByIngredient.text=drink.ingrediente6
        ingrediente7ByIngredient.text=drink.ingrediente7
        ingrediente8ByIngredient.text=drink.ingrediente8
        ingrediente9ByIngredient.text=drink.ingrediente9
        ingrediente10ByIngredient.text=drink.ingrediente10
        medida1ByIngredient.text=drink.Measure1
        medida2ByIngredient.text=drink.Measure2
        medida3ByIngredient.text=drink.Measure3
        medida4ByIngredient.text=drink.Measure4
        medida5ByIngredient.text=drink.Measure5
        medida6ByIngredient.text=drink.Measure6
        medida7ByIngredient.text=drink.Measure7
        medida8ByIngredient.text=drink.Measure8
        medida9ByIngredient.text=drink.Measure9
        medida10ByIngredient.text=drink.Measure10

        AddToMenu.setOnClickListener{
            price= CoctelPrice.text.toString()
            addDrink()
        }


    }

}