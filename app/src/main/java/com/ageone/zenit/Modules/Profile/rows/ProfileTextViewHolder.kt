package com.ageone.zenit.Modules.Profile.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class ProfileTextViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewInfo by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.textColor = Color.parseColor("#00ACEB")
        textView.initialize()
        textView
    }

    val textViewValue by lazy {
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

fun ProfileTextViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewInfo,
        textViewValue
    )

    textViewInfo
        .constrainTopToTopOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,16)

    textViewValue
        .constrainTopToBottomOf(textViewInfo,8)
        .fillHorizontally(16)
}

fun ProfileTextViewHolder.initialize(info: String, text: String) {

    textViewInfo.text = info
    textViewValue.text = text

}
