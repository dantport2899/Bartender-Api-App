package com.facu.bartender.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
//view holder para los adaptadores de los recycleview para AdaptadorSearchByName,AdaptadorSearchByRandom,AdaptadorSearchByIngredient
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun bind(item: T,position: Int)
}