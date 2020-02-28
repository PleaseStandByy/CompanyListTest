package com.example.companylisttest.listAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.companylisttest.listAdapter.interfaces.OnItemClickListner

abstract class AbsHolder(itemView: View) :  RecyclerView.ViewHolder(itemView){

    var onItemClickListner : OnItemClickListner? = null

    abstract fun bind(item: Any?)
}