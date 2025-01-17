package com.example.msareefapp.Ui.Fragments.MostPuschased

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.entitys.MostPurchased
import com.example.domain.useCases.StaticsUseCases.GetMostPurchasedItemsUseCase
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.databinding.MostPurchasedBuilderBinding

class MostPurchasedAdapter (private val items : MutableList<MostPurchased?>?) : BaseAdapter<MostPurchased,MostPurchasedBuilderBinding>(items) {
    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): MostPurchasedBuilderBinding {
        return MostPurchasedBuilderBinding.inflate(inflater,parent,false)
    }

    override fun getViewHolder(binding: MostPurchasedBuilderBinding): BaseViewHolder<MostPurchased, MostPurchasedBuilderBinding> {
        return ItemViewHolder(binding)
    }

    class ItemViewHolder(val binding: MostPurchasedBuilderBinding) : BaseViewHolder<MostPurchased,MostPurchasedBuilderBinding>(binding){
        override fun bind(item: MostPurchased) {
            binding.itemName.text = item.name
            binding.ItemCount.text = item.number.toString()
            binding.itemsTotalPrice.text = "${item.total}$"
        }
    }
}