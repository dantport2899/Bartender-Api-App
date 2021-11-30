package com.facu.bartender.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facu.bartender.R
import com.facu.bartender.data.DatosRecibo
//adaptador del recycleviewer del recibo
class AdapterRecibo(private val listaRecibo: ArrayList<DatosRecibo>): RecyclerView.Adapter<AdapterRecibo.ViewHolder>(){


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemNombre: TextView = itemView.findViewById(R.id.NombreCoctelReciboTXT)
        var itemPrecio: TextView = itemView.findViewById(R.id.PrecioCoctelesReciboTXT)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val f = LayoutInflater.from(parent.context).inflate(R.layout.recibo_cardview,parent, false)
        return ViewHolder(f)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem=listaRecibo[position]
        holder.itemPrecio.text=currentItem.PrecioRecibo
        holder.itemNombre.text=currentItem.NameRecibo
    }

    override fun getItemCount(): Int {
        return listaRecibo.size
    }


}