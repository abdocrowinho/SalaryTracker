package com.example.msareefapp.Ui.BindingAdapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:HelperText")
fun setHelperText (layout: TextInputLayout,helperText:String){

    layout.helperText=helperText
}