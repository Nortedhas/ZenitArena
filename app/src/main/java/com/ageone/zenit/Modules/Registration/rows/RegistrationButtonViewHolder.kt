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

class RegistrationButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val buttonRegistration by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#00ACEB"))
        button.textColor = Color.WHITE
        button.textSize = 20F
        button.cornerRadius = 16.dp
        button.text = "Зарегистрироваться"
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button
    }

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

fun RegistrationButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonRegistration,
        textViewConvention
    )

    buttonRegistration
        .constrainTopToTopOf(constraintLayout,28)
        .fillHorizontally(16)
        .height(53)

    textViewConvention
        .constrainTopToBottomOf(buttonRegistration, 19)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun RegistrationButtonViewHolder.initialize() {
    val text = "Нажимая на кнопку \"Зарегистрироваться\", я\n соглашаюсь на обработку\n"
    val declaration = "персональных данных"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#007aff")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textViewConvention.text = spannableContent

}
