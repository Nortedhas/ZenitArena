package com.ageone.zenit.Modules.Quiz.rows

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class QuizButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val buttonQuiz by lazy {
        val button = BaseButton()
        button.setBackgroundColor(Color.parseColor("#00ACEB"))
        button.textColor = Color.WHITE
        button.textSize = 20F
        button.cornerRadius = 16.dp
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button
    }

    init {

        renderUI()
    }

}

fun QuizButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        buttonQuiz
    )

    buttonQuiz
        .constrainTopToTopOf(constraintLayout,29)
        .constrainBottomToBottomOf(constraintLayout,15)
        .fillHorizontally(16)
        .height(53)
}

fun QuizButtonViewHolder.initialize(text: String) {
    buttonQuiz.text = text
}
