package com.ageone.zenit.Modules.Status.rows

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class StatusTitleBeforePresentViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val textViewTitle by lazy {
        val textView = BaseTextView()
        textView.gravity = Gravity.START
        textView.typeface = Typeface.DEFAULT_BOLD
        textView.textSize = 22F
        textView.textColor = Color.parseColor("#00ACEB")
        textView.setBackgroundColor(Color.TRANSPARENT)
        textView
    }

    init {

        renderUI()
    }

}

fun StatusTitleBeforePresentViewHolder.renderUI() {
    constraintLayout.subviews(
        textViewTitle
    )

    constraintLayout.setBackgroundColor(Color.parseColor("#FAFBFF"))

    textViewTitle
        .constrainTopToTopOf(constraintLayout, 16)
        .fillHorizontally(24)
        .constrainBottomToBottomOf(constraintLayout, 16)


}

fun StatusTitleBeforePresentViewHolder.initialize(count: Int) {
    textViewTitle.text = "До следующего приза осталось матчей: $count"
}
