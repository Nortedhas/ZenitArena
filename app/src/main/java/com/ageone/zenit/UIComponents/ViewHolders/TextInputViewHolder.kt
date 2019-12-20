package com.ageone.zenit.UIComponents.ViewHolders

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class TextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInput by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))
        textInput.setBoxBackgroundMode(TextInputLayout.BOX_BACKGROUND_OUTLINE)
        textInput.setBoxCornerRadii(6F,6F,6F,6F)

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 17F
            editText.maxLines = 1
            editText.setSingleLine(true)
        }
        textInput
    }

    init {

        renderUI()
    }

}

fun TextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInput
    )

    textInput
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)
        .height(55)
        .setPadding(6.dp,0,0,0)

}

fun TextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInput.hint = hint
    textInput.defineType(type)
}
