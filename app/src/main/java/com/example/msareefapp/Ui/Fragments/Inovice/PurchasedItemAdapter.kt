package com.example.msareefapp.Ui.Fragments.Inovice

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.entitys.PurchasedItem
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.databinding.PurchasedItemBuilderBinding

class PurchasedItemAdapter(purchasedItems: MutableList<PurchasedItem?>?) :
    BaseAdapter<PurchasedItem, PurchasedItemBuilderBinding>(purchasedItems) {
    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): PurchasedItemBuilderBinding {
        return PurchasedItemBuilderBinding.inflate(inflater,parent,false)
    }

    override fun getViewHolder(binding: PurchasedItemBuilderBinding): BaseViewHolder<PurchasedItem, PurchasedItemBuilderBinding> {
        return ItemViewHolder(binding)
    }
    private class ItemViewHolder(val binding : PurchasedItemBuilderBinding) : BaseViewHolder<PurchasedItem,PurchasedItemBuilderBinding>(binding) {
        override fun bind(item: PurchasedItem) {
            binding.purchasedPrice.text = item.price.toString()
            binding.purchasedName.text=item.name
        }
    }

}
