package com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.domain.entitys.Category
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.PurchasedItem
import com.example.msareefapp.Bases.BaseBottomSheet
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import com.example.msareefapp.Ui.sharedViewModels.SharedInvoiceViewModel
import com.example.msareefapp.Utiltes.getDate
import com.example.msareefapp.Utiltes.getDayOfWeek
import com.example.msareefapp.databinding.AddInvoiceBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddInvoiceBottomSheetFragment :
    BaseBottomSheet<AddInvoiceBottomSheetBinding, BottomSheetViewModel>() {
    private lateinit var itemsRowAdapter: EnterPurchasedItemRowAdapter
    private var items: MutableList<PurchasedItem?> = mutableListOf()

    private val _sharedViewModel: SharedInvoiceViewModel by activityViewModels ()
    private val _viewModel :BottomSheetViewModel by viewModels()

    override fun initViewModel(): BottomSheetViewModel {
        return _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): AddInvoiceBottomSheetBinding {
        return AddInvoiceBottomSheetBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observeUiMessage()
        observable()
        _viewModel.getCategories()

    }

    private fun observable() {
        _sharedViewModel.uiMessageLiveData.observe(viewLifecycleOwner) { uiMessage ->
            uiMessage?.let {
                showDialog(it)
            }
        }
        _sharedViewModel.invoiceTypeLiveData.observe(viewLifecycleOwner){ error ->
            if (error!!.isEmpty()){
                binding?.typeInvoice?.helperText = null
                binding?.typeInvoice?.isHelperTextEnabled =false

            }else {
                binding?.typeInvoice?.helperText=error.joinToString ("\n" )
                Log.d("invoiceTypeError",error.joinToString("\n"))
                binding?.typeInvoice?.isHelperTextEnabled= true

            }

        }
        _viewModel.getCategoriesLiveData.observe(viewLifecycleOwner){categories->
            val adapter = ArrayAdapter(requireContext(),R.layout.auto_complate_builder, categories!!.toMutableList())
            binding?.autoCompleteInvoiceType?.setAdapter(adapter)

            Log.d("categories are : ", categories.toString())

        }

    }

    private fun initViews() {
        binding?.dayTimeTv?.text = getDayOfWeek()
        binding?.dateTimeTv?.text = getDate()
        itemsRowAdapter = EnterPurchasedItemRowAdapter(items)

        binding?.purchasedItemsRv?.adapter = itemsRowAdapter
        binding?.apply {
            addNewItemBtn.setOnClickListener {
                val newItem = PurchasedItem()
                items.add(newItem)
                itemsRowAdapter.notifyItemInserted(items.size.minus(1))
            }
            doneBtn.setOnClickListener {
                val isValid = itemsRowAdapter.itemsValidate()
                _sharedViewModel.doneButton(
                    isValid, Invoice(
                        purchasedItems = items,
                        dateTime = binding!!.dateTimeTv.text.toString(),
                        time = binding!!.dayTimeTv.text.toString(),
                        amount = items.filterNotNull().sumOf { it.price ?:0.0}
                    ),
                    binding?.autoCompleteInvoiceType?.text.toString()
                )

            }
        }
    }
}