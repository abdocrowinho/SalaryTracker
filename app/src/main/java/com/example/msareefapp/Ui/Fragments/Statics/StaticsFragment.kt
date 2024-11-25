package com.example.msareefapp.Ui.Fragments.Statics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.databinding.FragmentStaticsBinding

class StaticsFragment : BaseFragment<FragmentStaticsBinding,StaticsViewMode>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding?.savingPercent?.setPercentage(50f)
        binding?.balancePercent?.setPercentage(30f)
        binding?.expensesPercent?.setPercentage(85f)


    }


    private val _viewModel : StaticsViewMode by viewModels()
    override fun initViewModel(): StaticsViewMode {
        return _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStaticsBinding {
        return FragmentStaticsBinding.inflate(inflater,container,false)
    }



}