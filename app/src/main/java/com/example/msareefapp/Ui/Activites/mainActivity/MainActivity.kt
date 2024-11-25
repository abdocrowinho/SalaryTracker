package com.example.msareefapp.Ui.Activites.mainActivity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.R
import com.example.msareefapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        linkNavControllerWithBottomNavBar()

    }

    private fun linkNavControllerWithBottomNavBar(){
        val navController = findNavController(R.id.nav_host_fragment_container)
        binding.bottomNavView.setupWithNavController(navController)
    }



}

