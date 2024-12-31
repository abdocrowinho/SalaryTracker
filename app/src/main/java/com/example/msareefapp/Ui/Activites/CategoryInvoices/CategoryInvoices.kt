package com.example.msareefapp.Ui.Activites.CategoryInvoices

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.R
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.ActivityCategoryInvoicesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryInvoices : BaseActivity<ActivityCategoryInvoicesBinding,CategoryInvoicesViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
        val categoryId = intent.extras?.getString(Constants.CATEGORY_ID)
        val categoryName = intent.extras?.getString(Constants.CATEGORY_NAME)
        Log.d("categoryId",categoryId.toString())
        _viewModel.getInvoicesByCategoryId(categoryId!!)
        binding.appBarTittleTv.text = categoryName
    }

    private fun initViews() {
        _viewModel.invoicesByCategoryLiveData.observe(this){
            val adapter = InvoicesAdapter(it.toMutableList())
            binding.InvoiceRcv.adapter=adapter
        }
        binding.materialToolBar.setNavigationOnClickListener {
            finish()
        }
    }

    private val _viewModel : CategoryInvoicesViewModel by viewModels()
    override fun initViewModel(): CategoryInvoicesViewModel {
        return _viewModel
    }

    override fun inflateBinding(): ActivityCategoryInvoicesBinding {
        return ActivityCategoryInvoicesBinding.inflate(layoutInflater)
    }

}