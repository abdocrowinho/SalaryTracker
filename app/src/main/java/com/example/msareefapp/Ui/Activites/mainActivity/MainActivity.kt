package com.example.msareefapp.Ui.Activites.mainActivity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.R
import com.example.msareefapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding,MainActivityViewModel>(){
   private val _viewModel : MainActivityViewModel by viewModels()
    override fun initViewModel(): MainActivityViewModel {
        return _viewModel
    }

    override fun inflateBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

}

