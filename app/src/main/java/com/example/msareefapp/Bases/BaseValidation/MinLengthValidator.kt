package com.example.msareefapp.Bases.BaseValidation

class MinLengthValidator( val minLength: Int) :Validator<String>{
    override fun validate(value: String): Boolean {
        return value.length >= minLength
    }

    override fun errorMessage(): String {
        return "الحقل ده لازم يحتوي على $minLength حرف"
    }

}