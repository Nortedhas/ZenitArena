package com.ageone.zenit.Modules.Quiz.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import com.google.android.material.textfield.TextInputLayout
import yummypets.com.stevia.*

class QuizTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputQuiz by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
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

fun QuizTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputQuiz
    )

    textInputQuiz
        .constrainTopToTopOf(constraintLayout,55)
        .fillHorizontally(16)

}

fun QuizTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputQuiz.hint = hint
    textInputQuiz.defineType(type)
}
