package com.facu.bartender

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
//adaptador del recycleviewer de billmodel
class Billsadapter(private val billlist : ArrayList<BillModel>) : RecyclerView.Adapter<Billsadapter.MyViewHolder>(){

    private var onClickDeleteItem: ((BillModel)->Unit)?= null
    private var onClickViewItem: ((BillModel)->Unit)?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Billsadapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.bills,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: Billsadapter.MyViewHolder, position: Int) {

        val bill : BillModel = billlist[position]
        holder.totalprice.text = bill.totalprice
        holder.date.text = bill.date
        holder.pricebill.text = bill.pricebill
        holder.pricetip.text = bill.pricetip
        holder.btnDelete.setOnClickListener{onClickDeleteItem?.invoke(bill)}
        holder.btnView.setOnClickListener{onClickViewItem?.invoke(bill)}
    }

    fun setOnClickDeleteItem(callback:(BillModel)->Unit ){
        this.onClickDeleteItem = callback
    }

    fun setOnClickViewItem(callback:(BillModel)->Unit ){
        this.onClickViewItem = callback
    }

    override fun getItemCount(): Int {
        return billlist.size
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val totalprice : TextView = itemView.findViewById(R.id.total)
        val date : TextView = itemView.findViewById(R.id.date)
        val pricebill : TextView = itemView.findViewById(R.id.bill)
        val pricetip : TextView = itemView.findViewById(R.id.tip)
        var btnDelete = itemView.findViewById<Button>(R.id.deleteBTN)
        var btnView = itemView.findViewById<Button>(R.id.viewBTN)
    }
}