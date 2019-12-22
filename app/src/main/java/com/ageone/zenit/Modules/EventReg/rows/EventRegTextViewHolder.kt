package com.ageone.zenit.Modules.EventReg.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class EventRegTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewReg by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#00B7FA")
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun EventRegTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewReg
    )
    constraintLayout
        .setBackgroundColor(Color.parseColor("#FAFBFF"))

    textViewReg
        .constrainTopToTopOf(constraintLayout, 29)
        .constrainBottomToBottomOf(constraintLayout, 29)
        .fillHorizontally(32)

}

fun EventRegTextViewHolder.initialize(text: String) {
    textViewReg.text = text
}
