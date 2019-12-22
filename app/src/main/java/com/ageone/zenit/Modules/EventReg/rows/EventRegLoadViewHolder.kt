package com.ageone.zenit.Modules.EventReg.rows

import android.graphics.Color
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class EventRegLoadViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewLoad by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#00ACEB")
        textView.text = "Загрузить PDF-файл"
        textView.initialize()
        textView
    }

    val textViewDescription by lazy {
        val textView = BaseTextView()
        textView.textSize = 12F
        textView.textColor = Color.parseColor("#787880")
        textView.text = "Для лиц, не достигших 18 лет, требуется\nсогласие от родителей в формате PDF"
        textView.gravity = Gravity.CENTER_HORIZONTAL
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun EventRegLoadViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewLoad,
        textViewDescription
    )

    textViewLoad
        .constrainTopToTopOf(constraintLayout,24)
        .constrainCenterXToCenterXOf(constraintLayout)

    textViewDescription
        .constrainTopToBottomOf(textViewLoad,24)
        .constrainCenterXToCenterXOf(constraintLayout)
}

fun EventRegLoadViewHolder.initialize() {

}
