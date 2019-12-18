package com.ageone.zenit.Modules.External.AutoComplete.rows

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import com.ageone.zenit.External.Base.View.BaseView
import yummypets.com.stevia.*

class ResultViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val lineTop by lazy {
        val view = BaseView()
        view.backgroundColor = Color.parseColor("#f0f0f0")
        view.orientation = GradientDrawable.Orientation.TOP_BOTTOM
        view.initialize()
        view
    }

    val primaryText by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.typeface = Typeface.DEFAULT
        textView.textColor = Color.parseColor("#333333")
        textView
    }

    val secondaryText by lazy {
        val textView = BaseTextView()
        textView.textSize = 14F
        textView.typeface = Typeface.DEFAULT
        textView.textColor = Color.parseColor("#AFAFB4")
        textView
    }

    init {

        renderUI()
    }

}

fun ResultViewHolder.renderUI() {
    constraintLayout.subviews(
        lineTop,
        primaryText,
        secondaryText
    )

    lineTop
        .fillHorizontally()
        .height(1)
        .constrainTopToTopOf(constraintLayout)
    primaryText
        .fillHorizontally(16)
        .constrainTopToTopOf(lineTop,5)

    secondaryText
        .fillHorizontally(16)
        .constrainTopToBottomOf(primaryText,3)
        .constrainBottomToBottomOf(constraintLayout,5)

}

fun ResultViewHolder.initialize(primary:String,secondary:String) {
    primaryText.text = primary
    secondaryText.text = secondary

}
