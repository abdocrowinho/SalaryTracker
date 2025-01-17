package com.example.msareefapp.Ui.Fragments.Categories

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.msareefapp.Bases.BaseFragment
import com.example.msareefapp.R
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.CategoriesStaticsBinding
import com.example.msareefapp.databinding.FragmentCategoriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoriesFragment : BaseFragment<FragmentCategoriesBinding,CategoriesViewModel>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        _viewModel.getCategoriesStats()

    }

    private fun observer() {
        _viewModel.categoriesLiveData.observe(viewLifecycleOwner){categoriesStats->
            Log.d("CategoriesStatics is ", categoriesStats.toString())
            val adapter = CategoriesAdapter(categoriesStats?.toMutableList())
            binding?.CategoriesRv?.adapter=adapter
            adapter.onItemClickListener = CategoriesAdapter.OnItemClick{ id, name->
                val  bundle = Bundle()
                bundle.putString(Constants.CATEGORY_ID,id)
                bundle.putString(Constants.CATEGORY_NAME,name)
                findNavController().navigate(R.id.categoryInvoices,bundle)

            }
        }
    }

    private  val _viewModel : CategoriesViewModel by  viewModels()
    override fun initViewModel(): CategoriesViewModel {
        return _viewModel
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoriesBinding {
        return FragmentCategoriesBinding.inflate(inflater,container,false)
    }


}