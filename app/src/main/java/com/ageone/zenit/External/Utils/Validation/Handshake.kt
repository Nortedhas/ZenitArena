package com.ageone.zenit.External.Utils.Validation

import com.ageone.zenit.Models.User.user

fun isValidUser(keyParameter: KeyParameterValidation) = when(keyParameter) {
    KeyParameterValidation.phone -> {
        !user.data.phone.isBlank()
    }
    KeyParameterValidation.email -> {
        !user.data.email.isBlank()
    }
}

enum class KeyParameterValidation {
    phone, email
}