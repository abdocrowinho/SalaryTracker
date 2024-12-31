package com.example.msareefapp.Ui.Fragments.profileFragment

import android.content.SharedPreferences
import android.content.res.Resources
import android.preference.PreferenceManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.domain.entitys.User
import com.example.domain.useCases.StaticsUseCases.GetUserUseCase
import com.example.domain.useCases.userUseCase.UpdateUserUseCase
import com.example.msareefapp.Bases.BaseValidation.FieldsValidation
import com.example.msareefapp.Bases.BaseValidation.MinLengthValidator
import com.example.msareefapp.Bases.BaseValidation.NotEmptyValidator
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Bases.UiMessage
import com.example.msareefapp.R
import com.example.msareefapp.Utiltes.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val updateUserUseCase: UpdateUserUseCase,
    private val getUserUseCase: GetUserUseCase

) : BaseViewModel(){

     fun setLanguage(lan: String,resources: Resources,refresh:()->Unit) {
        val locale = Locale(lan)
        val res : Resources = resources
        val dm = res.displayMetrics
        val conf = res.configuration
        conf.setLocale(locale)
        res.updateConfiguration(conf,dm)
        refresh()
    }

}