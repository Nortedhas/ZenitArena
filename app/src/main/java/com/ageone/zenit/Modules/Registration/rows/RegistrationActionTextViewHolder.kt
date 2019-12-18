package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class RegistrationActionTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewAction by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#A4A4A4")
        textView.textSize = 13F
        textView.typeface = Typeface.DEFAULT
        textView.gravity = Gravity.START
        textView
    }

    init {

        renderUI()
    }

}

fun RegistrationActionTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewAction
    )

    textViewAction
        .constrainTopToTopOf(constraintLayout, 5)
        .fillHorizontally(16)
}

fun RegistrationActionTextViewHolder.initialize() {

    val text = "Зачем это нужно?\n"
    val declaration = "Положение о волонтёрской деятельности"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#007aff")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textViewAction.text = spannableContent
}
