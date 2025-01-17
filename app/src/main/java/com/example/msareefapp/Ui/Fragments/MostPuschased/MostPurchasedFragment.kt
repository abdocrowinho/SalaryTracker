package com.example.msareefapp.Ui.Fragments.MostPuschased

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.entitys.MostPurchased
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.databinding.FragmentMostPurchasedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MostPurchasedFragment : BaseFragment<FragmentMostPurchasedBinding,MostPurchasedViewModel>() {
    private val _viewModel : MostPurchasedViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        _viewModel.getMostPurchasedItem()
    }

    private fun observer() {
        _viewModel.mostPurchasedItemsLivedata.observe(viewLifecycleOwner){mostPurchasedList->
            initViews(mostPurchasedList)
        }
    }

    private fun initViews(items : List<MostPurchased?>?){
val adapter = MostPurchasedAdapter(items?.toMutableList())
        binding?.mostPurchasedRc?.adapter = adapter
    }


    override fun initViewModel(): MostPurchasedViewModel {
      return  _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMostPurchasedBinding {
        return  FragmentMostPurchasedBinding.inflate(inflater,container,false)
    }
}