package com.example.msareefapp.Ui.Fragments.bottomSheetAddInvoice

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.domain.entitys.Invoice
import com.example.domain.entitys.PurchasedItem
import com.example.msareefapp.Bases.BaseBottomSheet
import com.example.msareefapp.R
import com.example.msareefapp.Ui.sharedViewModels.SharedInvoiceViewModel
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.Utiltes.getDate
import com.example.msareefapp.Utiltes.getDayOfWeek
import com.example.msareefapp.databinding.AddInvoiceBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date
import java.util.Locale

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

    private fun getLan (): Locale {
        val pref = context?.getSharedPreferences(Constants.SHARED_PREF , Context.MODE_PRIVATE)
        val lanOn = pref?.getString(Constants.DEFAULT_LANGUAGE,Constants.ENGLISH)
        Log.d("lan is working now ",lanOn.toString())
     val locale =  when(lanOn){
            "en" ->  Locale("en","Us")
            "ar"-> Locale("ar","Eg")
         else ->  Locale("en","Us")
     }
        return locale
    }
    private fun initViews() {
        val pref = context?.getSharedPreferences(Constants.SHARED_PREF , Context.MODE_PRIVATE)
        val lanOn = pref?.getString(Constants.DEFAULT_LANGUAGE,Constants.ENGLISH)
        binding?.dayTimeTv?.text = getDayOfWeek(getDate(getLan()),getLan())
        binding?.dateTimeTv?.text = getDate(getLan())
        itemsRowAdapter = EnterPurchasedItemRowAdapter(items)

        binding?.purchasedItemsRv?.adapter = itemsRowAdapter
        binding?.apply {
            addNewItemBtn.setOnClickListener {
                val newItem = PurchasedItem()
                items.add(newItem)
                itemsRowAdapter.notifyItemInserted(items.size.minus(1))
            }
            fun convertDayStringToNum() : Int{
                Log.d("sharedPref on work ",pref.toString())

                Log.d("lanonwork ",lanOn.toString())
                Log.d("today is ",binding!!.dayTimeTv.text.toString())
            val  day : Int =  if (lanOn.equals(Constants.ENGLISH)){
                   when(binding?.dayTimeTv?.text){
                        "Saturday" ->0
                        "Sunday"->1
                        "Monday"->2
                        "Tuesday"->3
                        "Wednesday"->4
                        "Thursday"->5
                        "Friday"->6
                        else -> {-1}
                    }

                }else {
                    when(binding?.dayTimeTv?.text){
                        "السبت" ->0
                        "الأحد"->1
                        "الأثنين"->2
                        "الثلاثاء"->3
                        "الأربعاء"->4
                        "الخميس"->5
                        "الجمعة"->6
                        else -> -1
                    }

                }
return day
            }
            doneBtn.setOnClickListener {
                val isValid = itemsRowAdapter.itemsValidate()
                _sharedViewModel.doneButton(
                    isValid, Invoice(
                        purchasedItems = items,
                        dateTime = binding!!.dateTimeTv.text.toString(),
                        time = convertDayStringToNum(),
                        amount = items.filterNotNull().sumOf { it.price ?:0.0}
                    ),
                    binding?.autoCompleteInvoiceType?.text.toString()
                )

            }
        }
    }
}