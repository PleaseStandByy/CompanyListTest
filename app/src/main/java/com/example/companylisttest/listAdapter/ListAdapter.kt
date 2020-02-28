package com.example.companylisttest.listAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.companylisttest.listAdapter.interfaces.OnItemClickListner
import kotlin.reflect.KClass
import kotlin.reflect.full.primaryConstructor


class ListAdapter<T>(var resId: Int, var holderType: KClass<out AbsHolder>) : RecyclerView.Adapter<AbsHolder>()
{
    private var items: ArrayList<T> = ArrayList()

    var size = items.size

    var onItemClickListner : OnItemClickListner? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsHolder {
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false)
        return holderType.primaryConstructor!!.call(view)
    }
    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: AbsHolder, position: Int) {
        if(onItemClickListner != null)
            holder.onItemClickListner = onItemClickListner
        holder.bind(items[position])
    }
    fun getItem(position: Int): T{
        return items.get(position)
    }
    fun getitems():ArrayList<T>{
        return items
    }
    fun setList(items: ArrayList<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun addItem(item: T){
        items.add(item)
        notifyDataSetChanged()
    }
}