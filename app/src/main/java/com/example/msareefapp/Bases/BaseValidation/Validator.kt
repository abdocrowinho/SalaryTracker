package com.example.msareefapp.Bases.BaseValidation

interface Validator<T> {
    fun validate(value : T):Boolean
    fun errorMessage():String
}