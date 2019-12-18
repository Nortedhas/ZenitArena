package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.*

class RegistrationPhotoViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewLoad by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#00ACEB")
        textView.textSize = 14F
        textView
    }

    val viewPhoto by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#D7D7D8")
        view.cornerRadius = 80.dp
        view.initialize()
        view
    }

    val buttonBackViewRegistration by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#00ACEB")
        view.cornerRadius = 6.dp
        view.initialize()
        view
    }

    val textViewRegistration by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 20F
        textView
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

fun RegistrationPhotoViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewLoad,
        viewPhoto,
        buttonBackViewRegistration,
        textViewRegistration,
        textViewConvention
    )

    textViewLoad
        .constrainTopToTopOf(constraintLayout,22)
        .constrainCenterXToCenterXOf(constraintLayout)

    viewPhoto
        .constrainTopToBottomOf(textViewLoad,17)
        .constrainCenterXToCenterXOf(constraintLayout)
        .height(80)
        .width(80)

    buttonBackViewRegistration
        .constrainTopToBottomOf(viewPhoto,28)
        .fillHorizontally(16)
        .height(53)

    textViewRegistration
        .constrainCenterXToCenterXOf(buttonBackViewRegistration)
        .constrainCenterYToCenterYOf(buttonBackViewRegistration)

    textViewConvention
        .constrainTopToBottomOf(buttonBackViewRegistration, 19)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun RegistrationPhotoViewHolder.initialize() {

    textViewLoad.text = "Загрузить фото"
    textViewRegistration.text = "Зарегистрироваться"

    val text = "Нажимая на кнопку \"Зарегистрироваться\", я\n соглашаюсь на обработку\n"
    val declaration = "персональных данных"

    val spannableContent = SpannableString(text + declaration)
    spannableContent.setSpan(
        ForegroundColorSpan(Color.parseColor("#007aff")),
        text.length,  text.length + declaration.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

    textViewConvention.text = spannableContent
}
