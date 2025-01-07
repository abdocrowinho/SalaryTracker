package com.example.msareefapp.Ui.Fragments.Inovice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice.AddInvoiceBottomSheetFragment
import com.example.msareefapp.Ui.sharedViewModels.SharedInvoiceViewModel
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.FragmentInvoicesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InvoicesFragment : BaseFragment<FragmentInvoicesBinding, InvoiceViewModel>() {
    private val _sharedViewModel: SharedInvoiceViewModel by activityViewModels()
private val _viewModel : InvoiceViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observer()
        _sharedViewModel.getAllInvoices()
    }

    private fun observer() {
        _viewModel.uiMessageLiveData.observe(viewLifecycleOwner){
            showDialog(it!!)
        }
        _sharedViewModel.invoicesLiveData.observe(viewLifecycleOwner) {
            val invoicesAdapter = InvoiceAdapter(it?.toMutableList())
            binding?.invoicesRv?.adapter = invoicesAdapter

            invoicesAdapter.onClickInvoiceListener = InvoiceAdapter.OnClick { invoice , pos->
                val bundle = Bundle()
                bundle.putParcelable(Constants.INVOICE_KEY, invoice)
                findNavController().navigate(R.id.updateInvoice, bundle)
            }
            if (!it.isNullOrEmpty()) {
                binding?.insertInvoiceTv?.isVisible = false
            }

            invoicesAdapter.onClickDeleteListener = InvoiceAdapter.OnClick { invoice, pos ->
initViewModel().deleteButton(invoice = invoice, negButton = {
hideDialog()
})
            }

        }
    }

    private fun initViews() {
        val bottomSheet = AddInvoiceBottomSheetFragment()
        binding?.apply {
            addNewInvoiceFab.setOnClickListener {
                bottomSheet.show(parentFragmentManager, null)
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
        return FragmentInvoicesBinding.inflate(inflater, container, false)
    }
}

