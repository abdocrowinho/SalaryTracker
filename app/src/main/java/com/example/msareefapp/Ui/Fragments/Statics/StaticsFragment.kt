package com.example.msareefapp.Ui.Fragments.Statics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.User
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.databinding.FragmentStaticsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StaticsFragment : BaseFragment<FragmentStaticsBinding, StaticsViewMode>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _viewModel.getUserData()
        observer()
        initViews()

    }

    private fun observer() {
        _viewModel.userLiveData.observe(viewLifecycleOwner) { user ->
            initUserData(user)
        }
        _viewModel.categoryStats.observe(viewLifecycleOwner){categoriesStats ->
            initStatics(categoriesStats)
        }

    }

    private fun initStatics(categoriesStats: List<CategoryStats?>?){
       val categoriesAdapter = CategoriesAdapter(categoriesStats?.toMutableList())
        binding?.CategoriesRv?.adapter = categoriesAdapter
    }

    private fun initUserData(user: User?) {
        user?.let {
            val salary = user.salary?.toFloat()
            val remainingSalary = user.remainingSalary?.toFloat()
            val expectedSaving = user.expectedSavings?.toFloat()
            val saving = (salary?.div(expectedSaving!!)?.times(100))
            val balance = (salary?.div(remainingSalary!!))?.times(100)
            val expenses = (salary?.minus(remainingSalary!!))?.div(salary)?.times(100)
            binding?.apply {
                remainingValue.text = user.remainingSalary
                actSalaryTV.text = user.salary
                userNameTV.text = user.userName
                savingPercent.setPercentage(saving!!)
                balancePercent.setPercentage(balance!!)
                expensesPercent.setPercentage(expenses!!)
            }
            _viewModel.getCategoryStats(userId = user.userId?:0)

        }
    }


    private fun initViews() {
    }


    private val _viewModel: StaticsViewMode by viewModels()
    override fun initViewModel(): StaticsViewMode {
        return _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStaticsBinding {
        return FragmentStaticsBinding.inflate(inflater, container, false)
    }


}