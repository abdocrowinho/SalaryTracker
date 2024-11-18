package com.example.msareefapp.Bases.BaseValidation

object FieldsValidation {

    fun <T>validateFields(value: T,validators:List<Validator<T>>):List<String>
    {
        return validators.filterNot { it.validate(value) }.map { it.errorMessage() }
    }
}