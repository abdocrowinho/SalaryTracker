package com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import com.example.domain.entitys.PurchasedItem
import com.example.msareefapp.Bases.BaseAdapter
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.R
import com.example.msareefapp.databinding.EnterPurchasedRowBinding

class EnterPurchasedItemRowAdapter(
    private val purchasedItem: MutableList<PurchasedItem?>?,
) :
    BaseAdapter<PurchasedItem, EnterPurchasedRowBinding>(purchasedItem) {


    override fun getViewBinding(
        inflater: LayoutInflater,
        parent: ViewGroup,
        attach: Boolean
    ): EnterPurchasedRowBinding {
        return EnterPurchasedRowBinding.inflate(inflater, parent, false)
    }

    fun itemsValidate(): Boolean {
        var isValid = true
        for (i in purchasedItem?.indices!!) {
            val minLengthValidator = MinLengthValidator(1)
            val notEmptyValidator = NotEmptyValidator()
            val name = purchasedItem[i]?.name.toString()
            val price = purchasedItem[i]?.price?.toString() ?: ""

            val priceFieldError = if (price.isEmpty()) {
                listOf("price must be not empty ")

            } else if (price.toDoubleOrNull() == null) {
                listOf("price must be not null ")

            } else if (price.toDoubleOrNull()!! <= 0) {
                listOf("price must be greater than zero ")
            } else {
                emptyList()
            }
            val itemNameFieldError = FieldsValidation.validateFields(
                name,
                listOf(minLengthValidator, notEmptyValidator)
            )
            if (priceFieldError.isNotEmpty() || itemNameFieldError.isNotEmpty()) {
                isValid = false
                break
            }
        }
        return isValid
    }

    override fun getViewHolder(binding: EnterPurchasedRowBinding): BaseViewHolder<PurchasedItem, EnterPurchasedRowBinding> {
        return ItemHolder(binding)
    }

    private class ItemHolder(val binding: EnterPurchasedRowBinding) :
        BaseViewHolder<PurchasedItem, EnterPurchasedRowBinding>(binding) {
        override fun bind(item: PurchasedItem) {

            val minLengthValidator = MinLengthValidator(1)
            val notEmptyValidator = NotEmptyValidator()
            binding.itemNameValue.addTextChangedListener {
                item.name = it.toString()
                val itemErrorValidation = FieldsValidation.validateFields(
                    item.name.toString(),
                    listOf(minLengthValidator, notEmptyValidator)
                )
                if (itemErrorValidation.isNotEmpty()) {

                    binding.itemNameContainer.helperText ="this field can't to be empty"

                    binding.itemNameContainer.isHelperTextEnabled = true
                } else {
                    binding.itemNameContainer.helperText = null
                    binding.itemNameContainer.isHelperTextEnabled = false
                }
            }






            binding.purchasedPriceValue.addTextChangedListener {
                val priceText = it.toString().trim()
                val price = priceText.toDoubleOrNull()
                item.price = it.toString().toDoubleOrNull()
                if (priceText.isEmpty()) {
                    binding.priceContainer.helperText = "You have to enter price!"
                    binding.priceContainer.isHelperTextEnabled = true
                } else if (price == null || price <= 0.0) {
                    binding.priceContainer.helperText = "Enter price correctly!"
                    binding.priceContainer.isHelperTextEnabled = true
                } else {
                    binding.priceContainer.isHelperTextEnabled = false
                    binding.priceContainer.helperText = null
                }
            }


        }
    }
}