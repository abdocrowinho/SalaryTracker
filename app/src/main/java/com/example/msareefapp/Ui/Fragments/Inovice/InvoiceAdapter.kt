package com.example.msareefapp.Ui.Fragments.Inovice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entitys.Invoice
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.databinding.InvoiceItemBinding

class InvoiceAdapter( val invoicesList:MutableList<Invoice?>?) : BaseAdapter<Invoice,InvoiceItemBinding>(invoicesList) {
    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): InvoiceItemBinding {
      return  InvoiceItemBinding.inflate(inflater,parent,false)
    }

    override fun getViewHolder(binding: InvoiceItemBinding): BaseViewHolder<Invoice, InvoiceItemBinding> {
        return ItemViewHolder(binding,onClickInvoiceListener,onClickDeleteListener)
    }
    private class ItemViewHolder(val  binding: InvoiceItemBinding, val onClickInvoiceListener : OnClick?, val onDeleteClickListener : OnClick?,) : BaseViewHolder<Invoice,InvoiceItemBinding>(binding) {
        override fun bind(item: Invoice) {
            val position = adapterPosition
            binding.purchasedItemsRV.adapter=PurchasedItemAdapter(item.purchasedItems?.toMutableList())
            binding.dateTv.text= item.dateTime.toString()
            binding.totalValue.text= item.amount.toString()
            binding.invoiceBuilder.setOnClickListener{
                if (position != RecyclerView.NO_POSITION){
                    onClickInvoiceListener?.onInvoiceClick(item,position)
                }
            }
            binding.deletItem.setOnClickListener{
                onDeleteClickListener?.onInvoiceClick(item, pos = position)
            }

        }
    }
     var onClickInvoiceListener:OnClick ?=null
    var onClickDeleteListener : OnClick ?= null
    fun interface OnClick{
        fun onInvoiceClick(invoice: Invoice,pos : Int)
    }
}