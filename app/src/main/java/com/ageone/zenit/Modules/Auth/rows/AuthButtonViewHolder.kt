package com.ageone.zenit.Modules.Auth.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.*

class AuthButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val buttonBackViewSignIn by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#00ACEB")
        view.cornerRadius = 6.dp
        view.initialize()
        view
    }

    val textViewSignIn by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.WHITE
        textView.textSize = 20F
        textView
    }

    val textViewPassword by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#BBBCBC")
        textView.textSize = 13F
        textView
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


    init {

        renderUI()
    }

}

fun AuthButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonBackViewSignIn,
        textViewSignIn,
        textViewPassword,
        buttonBackViewRegistration,
        textViewRegistration
    )

    buttonBackViewSignIn
        .constrainTopToTopOf(constraintLayout,27)
        .fillHorizontally(16)
        .height(50)

    textViewSignIn
        .constrainCenterXToCenterXOf(buttonBackViewSignIn)
        .constrainCenterYToCenterYOf(buttonBackViewSignIn)

    textViewPassword
        .constrainTopToBottomOf(buttonBackViewSignIn,18)
        .constrainCenterXToCenterXOf(constraintLayout)

    buttonBackViewRegistration
        .constrainTopToBottomOf(textViewPassword,29)
        .fillHorizontally(16)
        .height(50)

    textViewRegistration
        .constrainCenterXToCenterXOf(buttonBackViewRegistration)
        .constrainCenterYToCenterYOf(buttonBackViewRegistration)
}

fun AuthButtonViewHolder.initialize() {

    textViewSignIn.text = "Войти"
    textViewPassword.text = "Забыли пароль?"
    textViewRegistration.text = "Зарегистрироваться"

}
