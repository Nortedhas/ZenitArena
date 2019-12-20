package com.ageone.zenit.Modules.Auth.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class AuthForgotPasswordViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewPassword by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#BBBCBC")
        textView.textSize = 13F
        textView
    }

    init {

        renderUI()
    }

}

fun AuthForgotPasswordViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewPassword
    )

    textViewPassword
        .constrainTopToTopOf(constraintLayout, 16)
        .constrainRightToRightOf(constraintLayout)
        .constrainLeftToLeftOf(constraintLayout)


}

fun AuthForgotPasswordViewHolder.initialize() {
    textViewPassword.text = "Забыли пароль?"
}
