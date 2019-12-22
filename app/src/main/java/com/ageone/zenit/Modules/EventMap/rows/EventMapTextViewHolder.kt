package com.ageone.zenit.Modules.EventMap.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews
import yummypets.com.stevia.textColor

class EventMapTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewMap by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.BLACK
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun EventMapTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewMap
    )

    textViewMap
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,16)

}

fun EventMapTextViewHolder.initialize(text: String) {
    textViewMap.text = text
}
