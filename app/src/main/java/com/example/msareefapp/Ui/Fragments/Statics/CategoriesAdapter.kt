package com.example.msareefapp.Ui.Fragments.Statics

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.Invoice
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.databinding.CategoriesStaticsBinding

class CategoriesAdapter(private val items :MutableList<CategoryStats?>?) : BaseAdapter<CategoryStats,CategoriesStaticsBinding>(items) {
    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): CategoriesStaticsBinding {
      return  CategoriesStaticsBinding.inflate(inflater,parent,false)
    }
override fun getViewHolder(binding: CategoriesStaticsBinding): BaseViewHolder<CategoryStats, CategoriesStaticsBinding> {
        return ItemViewHolder(binding)
    }

 private class ItemViewHolder(val binding: CategoriesStaticsBinding) : BaseViewHolder<CategoryStats,CategoriesStaticsBinding>(binding){
     override fun bind(item: CategoryStats) {
         binding.tottalSumTv.text=item.totalExpenses.toString()
         binding.numberOfInvoicesId.text=item.numberOfInvoices.toString()
         binding.categoryType.text = item.name
         binding.percentView.setPercentage((item.totalExpenses?.div(item.salary)?.times(100))!!.toFloat())
     }
 }
}