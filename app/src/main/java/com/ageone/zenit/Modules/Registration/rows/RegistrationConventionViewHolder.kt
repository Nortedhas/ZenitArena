package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class RegistrationConventionViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewConvention by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.CENTER
        textView.textSize = 13F
        textView.textColor = Color.parseColor("#A4A4A4")
        textView
    }

    init {

        renderUI()
    }

}

fun RegistrationConventionViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewConvention
    )

    textViewConvention
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun RegistrationConventionViewHolder.initialize() {
    val text = "Нажимая на кнопку \"Зарегистрироваться\", я\n соглашаюсь на обработку\n"
    val declaration = "персональных данных"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#00ACEB")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textViewConvention.text = spannableContent

}
