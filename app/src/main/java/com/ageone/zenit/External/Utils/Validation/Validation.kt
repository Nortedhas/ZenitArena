package com.ageone.zenit.External.Utils.Validation

import java.util.regex.Pattern.compile

fun String.isValidPhone() : Boolean {
    return length == "+7 (965) 865-82-56".length
}

fun String.isValidEmail() : Boolean {
    val emailRegex = compile("(?:[a-zA-Z0-9!#$%\\&‘*+/=?\\^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%\\&'*+/=?\\^_`{|}" +
    "~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\" +
            "x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-" +
            "z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5" +
            "]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-" +
            "9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21" +
            "-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    return emailRegex.matcher(this).matches()
}

fun String.toCorrectPhone() = Regex("\\D+").replace(this, "")

fun String.toBeautifulPhone(): String = if (length > 10) {
    ("+" + this[0] + " (" + this[1] + this[2] + this[3] + ") " +
            this[4] + this[5] + this[6] + "-" + this[7] + this[8] + "-" + this[9] + this[10])
} else this
