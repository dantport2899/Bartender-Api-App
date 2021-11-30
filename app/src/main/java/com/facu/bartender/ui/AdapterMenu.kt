package com.facu.bartender.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facu.bartender.DrinkModel
import com.facu.bartender.R
//adaptador del recycleviewer de adaptaerMenu
class AdapterMenu: RecyclerView.Adapter<AdapterMenu.MenuViewHolder>() {

    private var stdList: ArrayList<DrinkModel> = ArrayList()
    private var onClickDeleteItem: ((DrinkModel)->Unit)?= null
    private var onClickAgregarItem: ((DrinkModel)->Unit)?=null
    private var onClickItem: ((DrinkModel)->Unit)?= null

    fun addItems(items:ArrayList<DrinkModel>){
        this.stdList=items
        notifyDataSetChanged()
    }

    fun setOnclickItem(callback: (DrinkModel)->Unit){
        this.onClickItem = callback
    }

    fun setOnClickDeleteItem(callback:(DrinkModel)->Unit ){
        this.onClickDeleteItem = callback
    }

    fun setOnClickAgregarItem(callback:(DrinkModel)->Unit){
         this.onClickAgregarItem=callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MenuViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.menu_items, parent, false)
    )
    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val std = stdList[position]
        holder.bindView(std)
        holder.itemView.setOnClickListener { onClickItem?.invoke(std)}
        holder.btnDelete.setOnClickListener{onClickDeleteItem?.invoke(std)}
        holder.OrdenAdd.setOnClickListener{onClickAgregarItem?.invoke(std)}
    }

    override fun getItemCount(): Int {
       return stdList.size
    }

    class MenuViewHolder(var view: View): RecyclerView.ViewHolder(view){

        private var id = view.findViewById<TextView>(R.id.IDCoctelmenuTXT)
        private var name = view.findViewById<TextView>(R.id.NombreCoctelmenuTXT)
        private var precio = view.findViewById<TextView>(R.id.PrecioCoctelesmenuTXT)
        var btnDelete = view.findViewById<Button>(R.id.deleteBTN)
        var OrdenAdd  = view.findViewById<Button>(R.id.OrdenAddBTN)
        fun bindView(std: DrinkModel){
            id.text= std.id.toString()
            name.text=std.name
            precio.text=std.price
        }
    }

}