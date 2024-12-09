package com.example.msareefapp.Bases.BaseValidation

class MinPriceValidator(private val minPrice: Int) :Validator<Int>{
    override fun validate(value: Int): Boolean {
        var isValid = true
if (value==null || value.toDouble()<0){
    isValid =false
}
        return  isValid
    }

    override fun errorMessage(): String {
        return "price can't be letter than Zero or zero "
    }

}