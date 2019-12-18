package com.example.ageone.Modules.Entry.rows

import android.graphics.Color

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class RegistrationTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val phoneTextView by lazy {
        val textView = BaseTextView()
        textView.textColor = Color.parseColor("#089988")
        textView.textSize = 16F
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    val textInputL by lazy {
        val textInput = BaseTextInputLayout()

        textInput.boxStrokeColor = Color.parseColor("#C1C1C1")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

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

fun RegistrationTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputL
    )

     textInputL
         .constrainTopToBottomOf(phoneTextView)
         .fillHorizontally(14)
}

fun RegistrationTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputL.hint = hint
    textInputL.defineType(type)
}
