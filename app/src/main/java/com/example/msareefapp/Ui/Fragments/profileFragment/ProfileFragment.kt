package com.example.msareefapp.Ui.Fragments.profileFragment

import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    private val _viewModel: ProfileViewModel by viewModels()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {
        val sharedPreferences = requireContext().getSharedPreferences(Constants.SHARED_PREF,Context.MODE_PRIVATE)
        val isNightMode = sharedPreferences.getBoolean(Constants.IS_NIGH_MODE,false)
        val defaultLan = sharedPreferences.getString(Constants.DEFAULT_LANGUAGE,Constants.ENGLISH)
        binding?.nightMode?.setIsNightMode(isNightMode)
        setIsNightMode(isNightMode)
        val countries: List<String> = listOf(getString(R.string.عربي), getString(R.string.english))
        val adapter = ArrayAdapter(requireContext(), R.layout.auto_complate_builder, countries)
        languageAutoComplete(adapter)

        binding?.editProfile?.setOnClickListener{
            findNavController().navigate(R.id.editProfileActivity)
        }
        binding?.nightMode?.setOnSwitchCheckedChangeListener {

           setIsNightMode(it)
        }
    }

    private fun setIsNightMode(isNightMode: Boolean) {
        val sharedPreferences = requireContext().getSharedPreferences(Constants.SHARED_PREF,Context.MODE_PRIVATE)
        val editor= sharedPreferences.edit()
        editor.putBoolean(Constants.IS_NIGH_MODE,isNightMode).apply()
        if (isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

    }

    private fun languageAutoComplete(adapter: ArrayAdapter<String>) {
        binding?.apply {
            lanActv.setAdapter(adapter)
            lanActv.setOnClickListener {
                binding?.lanActv?.showDropDown()
            }
            lanActv.setText(getLocalLan(),false)
            lanActv.setOnItemClickListener{parent,view,pos,id->
val selectedItem = parent.getItemAtPosition(pos)


             val lan = when(selectedItem){
                "arabic"-> "ar"
                 "English"->"en"
                 else -> "en"
             }
          val defaultLan = saveLanInSharedPref(lan)
val res : Resources = resources
               _viewModel.setLanguage(defaultLan!!,res){
                   findNavController().navigate(R.id.mainActivity)
               }
                Log.d("selectedLanIS",selectedItem.toString())
            }
        }
    }

    private fun saveLanInSharedPref(selectedItem: String) : String? {
        val sharedPreferences = requireContext().getSharedPreferences(Constants.SHARED_PREF,Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(Constants.DEFAULT_LANGUAGE,selectedItem).apply()
        val defaultLan = sharedPreferences.getString(Constants.SHARED_PREF,selectedItem)
        return defaultLan
    }


    private fun getLocalLan(): String {
        val localLan= Locale.getDefault().language
        val defaultLan = when (localLan) {
            "ar" -> getString(R.string.عربي)
            "en" -> getString(R.string.english)
            else -> getString(R.string.english)
        }
        return defaultLan
    }


    override fun initViewModel(): ProfileViewModel {
        return _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

}