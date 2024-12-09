package com.example.msareefapp.Ui.Fragments.Inovice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice.AddInvoiceBottomSheetFragment
import com.example.msareefapp.databinding.FragmentInvoicesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvoicesFragment :BaseFragment<FragmentInvoicesBinding,InvoiceViewModel>(){
    private val _viewModel : InvoiceViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
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
        return _viewModel
    }
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentInvoicesBinding {
        return FragmentInvoicesBinding.inflate(inflater,container,false)
    }
}

