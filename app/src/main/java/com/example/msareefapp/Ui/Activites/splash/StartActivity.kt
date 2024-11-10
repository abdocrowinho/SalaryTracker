package com.example.msareefapp.Ui.Activites.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.Bases.BaseViewModel
import com.example.msareefapp.Ui.Activites.onBorading.OnBoardingActivity
import com.example.msareefapp.databinding.ActivityStartBinding

class StartActivity : BaseActivity<ActivityStartBinding,BaseViewModel>() {
  private  val _viewModel :BaseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToOnBoarding()


    }

    private fun navigateToOnBoarding() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,OnBoardingActivity::class.java)

            startActivity(intent)
        },4000)
    }


    override fun initViewModel(): BaseViewModel {
        return _viewModel
    }

    override fun inflateBinding(): ActivityStartBinding {
        return ActivityStartBinding.inflate(layoutInflater)
    }
}