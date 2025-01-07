package com.example.msareefapp.Ui.Activites.UpdateInvoice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.entitys.Category
import com.example.domain.entitys.Invoice
import com.example.domain.useCases.InvoicesUseCases.UpdateInvoiceUseCase
import com.example.domain.useCases.categoriesUseCase.GetCategoriesUseCase
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpdateInvoiceViewModel @Inject constructor(
):BaseViewModel() {


}