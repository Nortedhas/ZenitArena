package com.ageone.zenit.Modules.Auth.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class AuthTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewWelcome by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#00ACEB")
        textView.textSize = 18F
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.text = "ДОБРО ПОЖАЛОВАТЬ!"
        textView
    }

    val textInputEmail by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
        textInput.setBoxCornerRadii(12F,12F,12F,12F)
        textInput.hint = "Email"
        textInput.defineType(InputEditTextType.EMAIL)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    val textInputPassword by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
        textInput.setBoxCornerRadii(12F,12F,12F,12F)
        textInput.hint = "Пароль"
        textInput.defineType(InputEditTextType.PASSWORD)
        textInput.setToggleForPassword(Color.BLACK)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    init {

        renderUI()
    }

}

fun AuthTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewWelcome,
        textInputEmail,
        textInputPassword
    )

    textViewWelcome
        .constrainTopToTopOf(constraintLayout,120)
        .constrainCenterXToCenterXOf(constraintLayout)

    textInputEmail
        .constrainTopToBottomOf(textViewWelcome,25)
        .fillHorizontally(16)
        .height(55)

    textInputPassword
        .constrainTopToBottomOf(textInputEmail,27)
        .fillHorizontally(16)
        .height(55)

    }

fun AuthTextInputViewHolder.initialize() {

}
