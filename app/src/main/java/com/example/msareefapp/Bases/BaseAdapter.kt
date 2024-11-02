package com.example.msareefapp.Bases

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private var items: MutableList<T>
) : RecyclerView.Adapter<BaseAdapter.ViewHolder<T, VB>>() {

    abstract class ViewHolder<T, VB : ViewBinding>(private val binding: VB) :
        RecyclerView.ViewHolder(binding.root) {
        abstract fun bind(item: T)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder<T, VB>, position: Int) {
        holder.bind(items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T, VB> {
        val binding = getViewBinding(LayoutInflater.from(parent.context), parent, false)
        return getViewHolder(binding)
    }

    abstract fun getViewBinding(inflater: LayoutInflater, parent: ViewGroup, attach: Boolean): VB
    abstract fun getViewHolder(binding: VB): ViewHolder<T, VB>

    fun updateItems(list: MutableList<T>) {
        items = list
        notifyDataSetChanged()
    }

}