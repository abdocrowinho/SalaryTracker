package com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.PurchasedItem
import com.example.msareefapp.Bases.BaseBottomSheet
import com.example.msareefapp.Bases.UiMessage
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

    private val _viewModel: BottomSheetViewModel by viewModels()

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

    }

    private fun observable() {
        _viewModel.uiMessageLiveData.observe(viewLifecycleOwner) { uiMessage ->
            uiMessage?.let {
                showDialog(it)
            }
        }
        _viewModel.invoiceTypeLiveData.observe(viewLifecycleOwner){ error ->
            if (error!!.isEmpty()){
                binding?.typeInvoice?.helperText = null
                binding?.typeInvoice?.isHelperTextEnabled =false

            }else {
                binding?.typeInvoice?.helperText=error.joinToString ("\n" )
                Log.d("invoiceTypeError",error.joinToString("\n"))
                binding?.typeInvoice?.isHelperTextEnabled= true

            }

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
                _viewModel.doneButton(
                    isValid, Invoice(
                        purchasedItems = items,
                        dateTime = binding!!.dateTimeTv.text.toString(),
                        time = binding!!.dayTimeTv.text.toString(),
                        amount = items.filterNotNull().sumOf { it.price ?:0.0}

                    ),
                    binding?.invoiceTypeValue?.text.toString()
                )
            }
        }
    }
}