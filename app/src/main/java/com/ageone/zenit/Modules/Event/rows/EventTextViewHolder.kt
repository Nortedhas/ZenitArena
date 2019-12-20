package com.ageone.zenit.Modules.Event.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class EventTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewMonth by lazy {
        val textView = BaseTextView()
        textView.textSize = 18F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun EventTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewMonth
    )

    textViewMonth
        .constrainTopToTopOf(constraintLayout,16)
        .constrainRightToRightOf(constraintLayout,16)
}

fun EventTextViewHolder.initialize(text: String) {
    textViewMonth.text = text
}
