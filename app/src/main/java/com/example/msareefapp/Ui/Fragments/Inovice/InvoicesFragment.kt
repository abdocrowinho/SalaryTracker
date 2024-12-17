package com.example.msareefapp.Ui.Fragments.Inovice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice.AddInvoiceBottomSheetFragment
import com.example.msareefapp.Ui.sharedViewModels.SharedInvoiceViewModel
import com.example.msareefapp.databinding.FragmentInvoicesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvoicesFragment :BaseFragment<FragmentInvoicesBinding,InvoiceViewModel>(){
    private val _sharedViewModel : SharedInvoiceViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observer()
        _sharedViewModel.getAllInvoices()
    }

    private fun observer() {
        _sharedViewModel.invoicesLiveData.observe(viewLifecycleOwner){
            val invoicesAdapter= InvoiceAdapter(it?.toMutableList())
            binding?.invoicesRv?.adapter=invoicesAdapter
            if (!it.isNullOrEmpty()){
                binding?.insertInvoiceTv?.isVisible=false
            }

        }
    }

    private fun initViews() {


        val bottomSheet = AddInvoiceBottomSheetFragment()
        binding?.apply {
            addNewInvoiceFab.setOnClickListener{
bottomSheet.show(parentFragmentManager,null)
            }
        }

    }


    override fun initViewModel(): InvoiceViewModel {
        return ViewModelProvider(this)[InvoiceViewModel::class.java]
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInvoicesBinding {
        return FragmentInvoicesBinding.inflate(inflater,container,false)
    }
}

