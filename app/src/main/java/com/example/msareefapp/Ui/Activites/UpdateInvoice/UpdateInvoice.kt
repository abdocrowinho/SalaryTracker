package com.example.msareefapp.Ui.Activites.UpdateInvoice

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.PurchasedItem
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.R
import com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice.EnterPurchasedItemRowAdapter
import com.example.msareefapp.Ui.sharedViewModels.SharedInvoiceViewModel
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.ActivityUpdateInvoiceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateInvoice : BaseActivity<ActivityUpdateInvoiceBinding,SharedInvoiceViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observer()
_sharedViewModel.getCategories()
    }

    private fun observer() {
        _sharedViewModel.categoriesLiveData.observe(this){categoriesList->
            val adapter = ArrayAdapter(this, R.layout.auto_complate_builder , categoriesList!!.toMutableList() )
            binding.autoCompleteInvoiceType.setAdapter(adapter)
        }
    }

    private fun initViews() {
        val  invoice = intent.getParcelableExtra<Invoice>(Constants.INVOICE_KEY)
        val items = invoice?.purchasedItems?.toMutableList() ?: mutableListOf()

        val adapter = EnterPurchasedItemRowAdapter(items)
        binding.purchasedItemsRv.adapter = adapter

        binding.apply { addNewItemBtn.setOnClickListener {
            val newItem = PurchasedItem()
            items.add(newItem)
            adapter.notifyItemInserted(items.size.minus(1))
        }
        binding.doneBtn.setOnClickListener {
         val isValidate =adapter.itemsValidate()
            _sharedViewModel.updateButton(invoice= invoice!!.copy(purchasedItems = items, amount = items.filterNotNull().sumOf{ it.price?:0.0 }),
                isValid = isValidate ,
                invoiceType = binding.autoCompleteInvoiceType.text.toString())
        }
        }
    }
    private val _sharedViewModel : SharedInvoiceViewModel by viewModels()

    override fun initViewModel(): SharedInvoiceViewModel {
return _sharedViewModel
    }

    override fun inflateBinding(): ActivityUpdateInvoiceBinding {
return ActivityUpdateInvoiceBinding.inflate(layoutInflater)
    }
}