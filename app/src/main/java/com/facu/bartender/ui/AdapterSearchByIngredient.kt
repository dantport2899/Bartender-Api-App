package com.facu.bartender.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.facu.bartender.R
import com.facu.bartender.base.BaseViewHolder
import com.facu.bartender.data.model.Drink
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.filasdecocteles.view.*
//adaptador del recycleviewer de serchbyingredient buscar por ID
class AdapterSearchByIngredient(private val context: Context, private val listaCocteles:List<Drink>,
                                private val itemClickListener:ClickEnCoctelListenerIngredient): RecyclerView.Adapter<BaseViewHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ByIngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.filasdecocteles,parent,false))
    }

    interface ClickEnCoctelListenerIngredient{
        fun ClickTragoIngredient(drink: Drink)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ByIngredientViewHolder -> holder.bind(listaCocteles[position],position)
        }
    }

    override fun getItemCount(): Int {
        return listaCocteles.size
    }

    inner class ByIngredientViewHolder(itemView: View): BaseViewHolder<Drink>(itemView){
        override fun bind(item: Drink, position: Int) {
            Picasso.get().load(item.imagen).into(itemView.img_coctel)
            itemView.NombreCoctel.text =item.nombre
            itemView.DescripcionCocteles.text = item.descripcion
            itemView.setOnClickListener{ itemClickListener.ClickTragoIngredient(item)}
        }
    }
}