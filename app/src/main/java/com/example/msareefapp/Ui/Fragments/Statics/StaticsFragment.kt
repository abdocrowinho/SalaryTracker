package com.example.msareefapp.Ui.Fragments.Statics

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.domain.entitys.CategoryStats
import com.example.domain.entitys.User
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.Ui.Fragments.Categories.CategoriesAdapter
import com.example.msareefapp.Ui.Fragments.Categories.CategoriesFragment
import com.example.msareefapp.Ui.Fragments.DaysFragment.DaysFragment
import com.example.msareefapp.Ui.Fragments.MostPuschased.MostPurchasedFragment
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.FragmentStaticsBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
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
    }




    private fun initUserData(user: User?) {
        user?.let {
            val salary = user.salary?.toFloat()
            val remainingSalary = user.remainingSalary?.toFloat()
            val expectedSaving = user.expectedSavings?.toFloat()
            val saving = (remainingSalary?.div(expectedSaving!!)?.times(100))
            val balance = (remainingSalary?.div(salary!!))?.times(100)
            val expenses = (salary?.minus(remainingSalary!!))?.div(salary)?.times(100)
            binding?.apply {
                remainingValue.text = user.remainingSalary
                actSalaryTV.text = user.salary
                userNameTV.text = user.userName
                savingPercent.setPercentage(saving!!)
                balancePercent.setPercentage(balance!!)
                expensesPercent.setPercentage(expenses!!)
            }

        }
    }


    private fun initViews() {
        replaceFragment(CategoriesFragment())
        tabsLayoutSetup()

    }

    private fun tabsLayoutSetup() {
        binding!!.tabsLayout.apply {
            addTab(newTab().setText("Category")
            )
            addTab(newTab().setText("Days"))
            addTab(newTab().setText("Most Purchased"))
        }

        binding?.tabsLayout?.addOnTabSelectedListener(object : OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> replaceFragment(CategoriesFragment())
                    1 -> replaceFragment(DaysFragment())
                   2-> replaceFragment(MostPurchasedFragment())


                }
            }


            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }
    fun  replaceFragment(fragment: Fragment){
        childFragmentManager.beginTransaction().replace(R.id.FragmentContainer,fragment).commit()
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