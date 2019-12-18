package com.ageone.zenit.Modules.Registration.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class RegistrationTitleViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val textViewRegistration by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#00ACEB")
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView
    }

    init {

        renderUI()
    }

}

fun RegistrationTitleViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewRegistration
    )

    textViewRegistration
        .constrainTopToTopOf(constraintLayout,120)
        .constrainCenterXToCenterXOf(constraintLayout)

}

fun RegistrationTitleViewHolder.initialize() {
    textViewRegistration.text = "РЕГИСТРАЦИЯ"

}
