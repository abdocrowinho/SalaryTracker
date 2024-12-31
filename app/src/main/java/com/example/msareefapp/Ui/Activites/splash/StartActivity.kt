package com.example.msareefapp.Ui.Activites.splash

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import com.example.msareefapp.Bases.BaseActivity
import com.example.msareefapp.Ui.Activites.mainActivity.MainActivity
import com.example.msareefapp.Ui.Activites.onBorading.OnBoardingActivity
import com.example.msareefapp.Utiltes.Constants
import com.example.msareefapp.databinding.ActivityStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding,SplashViewModel>() {
  private  val _viewModel :SplashViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _viewModel.getUser()
        observer()
        val sharedPreferences = getSharedPreferences(Constants.SHARED_PREF,Context.MODE_PRIVATE)
       val  isNightMode=sharedPreferences.getBoolean(Constants.IS_NIGH_MODE,false)

        val defaultValue = sharedPreferences.getString(Constants.DEFAULT_LANGUAGE,Constants.ENGLISH)
        val res : Resources = resources
     _viewModel.setLanguage(defaultValue!!,res)

        if (isNightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }


    private fun observer() {
        _viewModel.getUserLifeData.observe(this){ user->
            if (user?.userId == null){
                Log.e("activity User ",user?.userName.toString())
                navigateToOnBoarding()

            }else {
             navigateToMainScreen()
            }
        }
    }



    private fun navigateToMainScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,MainActivity::class.java)

            startActivity(intent)
        },4000)
    }


    private fun navigateToOnBoarding() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this,OnBoardingActivity::class.java)

            startActivity(intent)
        },4000)
    }


    override fun initViewModel(): SplashViewModel {
        return _viewModel
    }

    override fun inflateBinding(): ActivityStartBinding {
        return ActivityStartBinding.inflate(layoutInflater)
    }
}