package com.example.msareefapp.Bases.BaseValidation

class NotEmptyValidator : Validator<String> {
    override fun validate(value: String): Boolean {
        return value.isNotBlank()
    }

    override fun errorMessage(): String {
        return "الحقل ده مينفعش يكون فاضي !"
    }
}