package com.ageone.zenit.Modules.FinalQuiz.rows

import android.graphics.Color
import android.graphics.Typeface
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class FinalQuizTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewFinalQuiz by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#00B7FA")
        textView.typeface = Typeface.DEFAULT
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun FinalQuizTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewFinalQuiz
    )

    constraintLayout
        .setBackgroundColor(Color.parseColor("#FAFBFF"))

    textViewFinalQuiz
        .constrainTopToTopOf(constraintLayout,16)
        .constrainBottomToBottomOf(constraintLayout,16)
        .fillHorizontally(32)


}

fun FinalQuizTextViewHolder.initialize(text: String) {
    textViewFinalQuiz.text = text
}
