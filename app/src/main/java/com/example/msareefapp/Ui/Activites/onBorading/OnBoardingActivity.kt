package com.example.msareefapp.Ui.Activites.onBorading

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.Ui.Activites.regestertion.RegisterActivity
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.ActivityOnBoradingBinding

class OnBoardingActivity : BaseActivity<ActivityOnBoradingBinding, OnBoardingViewModel>() {
    private val _viewModel: OnBoardingViewModel by viewModels()
    private var adapter: BoardingAdapter? = null
    var position = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        observableLifeData()
    }

    private fun observableLifeData() {
        _viewModel.setItemCount(binding.RcItemBinding.adapter?.itemCount ?: 0)
        _viewModel.position.observe(this) { pos ->

                position = pos

                binding.RcItemBinding.smoothScrollToPosition(position)


        }
        _viewModel.navigateLifeData.observe(this){shouldNavigate->
            if (shouldNavigate){
                navigateToRegisterActivity()
            }

        }
    }

    private fun initViews() {
        binding.skipBtn.setOnClickListener {
            navigateToRegisterActivity()
        }
        binding.nextBtn.setOnClickListener {
            _viewModel.navigateToNextItem()
        }

        adapter = BoardingAdapter(Constants.boardingList.toMutableList())
        binding.RcItemBinding.adapter = adapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(binding.RcItemBinding)
        tabLayoutHandler(adapter!!, snapHelper)


    }


    private fun navigateToRegisterActivity() {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

    private fun tabLayoutHandler(adapter: BoardingAdapter, snapHelper: LinearSnapHelper) {
        for (i in 0 until adapter.itemCount) {
            binding.tabLayout.addTab(binding.tabLayout.newTab())
        }

        binding.RcItemBinding.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val snapView = snapHelper.findSnapView(recyclerView.layoutManager) ?: return
                position = recyclerView.layoutManager?.getPosition(snapView) ?: return
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    override fun initViewModel(): OnBoardingViewModel {
        return _viewModel

    }

    override fun inflateBinding(): ActivityOnBoradingBinding {
        return ActivityOnBoradingBinding.inflate(layoutInflater)
    }
}