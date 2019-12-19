package com.ageone.zenit.Modules.Auth.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class AuthButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val buttonAuth by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#00ACEB"))
        button.textColor = Color.WHITE
        button.textSize = 20F
        button.cornerRadius = 16.dp
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button
    }

    //если сделать этот textView в отдельном вьюхолдере, то он либо выше, либо ниже кнопок. И да, это костыльно
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

fun AuthButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonAuth,
        textViewPassword
    )

    buttonAuth
        .constrainTopToTopOf(constraintLayout,27)
        .fillHorizontally(16)
        .height(50)

    textViewPassword
        .constrainTopToBottomOf(buttonAuth, 18)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun AuthButtonViewHolder.initialize(text: String) {
    buttonAuth.text = text

    textViewPassword.text = "Забыли пароль?"
}
