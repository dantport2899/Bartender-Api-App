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
//adaptador del recycleviewe de serchbyrandom buscar por random
class AdapterSearchByRandom (private val context: Context, private val listaCocteles:List<Drink>,
                             private val itemClickListener: ClickEnCoctelListenerRandom): RecyclerView.Adapter<BaseViewHolder<*>>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return ByRandomViewHolder(LayoutInflater.from(context).inflate(R.layout.filasdecocteles,parent,false))
    }
    interface ClickEnCoctelListenerRandom{
        fun ClickTragoRandom(drink: Drink)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is ByRandomViewHolder -> holder.bind(listaCocteles[position],position)
        }
    }

    override fun getItemCount(): Int {
        return listaCocteles.size
    }

    inner class ByRandomViewHolder(itemView: View): BaseViewHolder<Drink>(itemView){
        override fun bind(item: Drink, position: Int) {
            Picasso.get().load(item.imagen).into(itemView.img_coctel)
            itemView.NombreCoctel.text =item.nombre
            itemView.DescripcionCocteles.text = item.descripcion
            itemView.setOnClickListener{ itemClickListener.ClickTragoRandom(item)}
        }
    }
}