package com.ageone.zenit.Modules.FinalQuiz.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextInputLayout.BaseTextInputLayout
import com.ageone.zenit.External.Base.TextInputLayout.InputEditTextType
import yummypets.com.stevia.*

class FinalQuizTextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textInputFinalQuiz by lazy {
        val textInput = BaseTextInputLayout()
        textInput.boxStrokeColor = Color.parseColor("#CBCBCB")
        textInput.setInactiveUnderlineColor(Color.rgb(193, 193, 193))

        textInput.editText?.let { editText ->
            editText.textColor = Color.parseColor("#000000")
            editText.textSize = 20F
            editText.maxLines = 2
            editText.setSingleLine(true)
        }
        textInput
    }

    init {

        renderUI()
    }

}

fun FinalQuizTextInputViewHolder.renderUI() {
    constraintLayout.subviews(
        textInputFinalQuiz
    )

    textInputFinalQuiz
        .constrainTopToTopOf(constraintLayout,24)
        .constrainBottomToBottomOf(constraintLayout,24)
        .fillHorizontally(32)
}

fun FinalQuizTextInputViewHolder.initialize(hint: String, type: InputEditTextType) {
    textInputFinalQuiz.hint = hint
    textInputFinalQuiz.defineType(type)
}
