package com.example.msareefapp.Ui.Activites.onBorading

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.databinding.ItemBoardingBinding

class BoardingAdapter(private val items : MutableList<ItemBoarding?>?) :
    BaseAdapter<ItemBoarding,ItemBoardingBinding>(items) {
    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): ItemBoardingBinding {
       return ItemBoardingBinding.inflate(inflater,parent,false)
    }





    override fun getViewHolder(binding: ItemBoardingBinding): BaseViewHolder<ItemBoarding, ItemBoardingBinding> {
return ItemViewHolder(binding)
    }
   private class ItemViewHolder(private val binding: ItemBoardingBinding ):BaseViewHolder<ItemBoarding,ItemBoardingBinding>(binding) {
        override fun bind(item: ItemBoarding) {
            binding.lottieAnimation.apply {
                setFailureListener{
Log.e("lottieError",it.toString())
                    }
                if (!isAnimating){
                    loop(true)
                    playAnimation()
                    setAnimation(item.animation)

                }


            }
            binding.TvDescription.text=item.description
        }
    }
}