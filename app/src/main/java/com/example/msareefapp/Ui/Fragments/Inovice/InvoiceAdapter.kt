package com.example.msareefapp.Ui.Fragments.Inovice

import android.view.LayoutInflater
import android.view.ViewGroup
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
        return ItemViewHolder(binding)
    }
    private class ItemViewHolder(val  binding: InvoiceItemBinding) : BaseViewHolder<Invoice,InvoiceItemBinding>(binding) {
        override fun bind(item: Invoice) {
            binding.purchasedItemsRV.adapter=PurchasedItemAdapter(item.purchasedItems?.toMutableList())
            binding.dateTv.text= item.dateTime.toString()
            binding.totalValue.text= item.amount.toString()
        }
    }
}