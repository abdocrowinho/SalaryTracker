package com.example.msareefapp.Ui.Fragments.Statics

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.transition.Visibility
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.Invoice
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.databinding.CategoriesStaticsBinding

class CategoriesAdapter(private val items :MutableList<CategoryStats?>?) : BaseAdapter<CategoryStats,CategoriesStaticsBinding>(items) {
    var onItemClickListener : OnItemClick?=null

    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): CategoriesStaticsBinding {
      return  CategoriesStaticsBinding.inflate(inflater,parent,false)
    }
override fun getViewHolder(binding: CategoriesStaticsBinding): BaseViewHolder<CategoryStats, CategoriesStaticsBinding> {
        return ItemViewHolder(binding,onItemClickListener)
    }


    private class ItemViewHolder(val binding: CategoriesStaticsBinding
    ,val listener:OnItemClick?
    ) : BaseViewHolder<CategoryStats,CategoriesStaticsBinding>(binding){

    override fun bind(item: CategoryStats) {
       if (item.numberOfInvoices != 0){
           binding.tottalSumTv.text=item.totalExpenses.toString()
           binding.numberOfInvoicesId.text=item.numberOfInvoices.toString()
           binding.categoryType.text = item.name
           binding.percentView.setPercentage((item.totalExpenses?.div(item.salary)?.times(100))!!.toFloat())
           binding.root.setOnClickListener{
               listener?.onClickListener(categoryId = item.id.toString(),item.name.toString())
           }
       }else{
           binding.percentView.visibility = View.GONE
       }

     }
 }
 fun  interface OnItemClick {
     fun onClickListener(categoryId:String,categoryName : String)
 }
}