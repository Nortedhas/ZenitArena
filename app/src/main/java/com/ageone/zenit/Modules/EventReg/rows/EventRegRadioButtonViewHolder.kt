package com.ageone.zenit.Modules.EventReg.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RadioButton.BaseRadioButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Base.TextView.BaseTextView
import yummypets.com.stevia.*

class EventRegRadioButtonViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val radioButtonReg by lazy {
        val radioButton = BaseRadioButton()
        radioButton.colors = intArrayOf(Color.GRAY, Color.parseColor("#00ACEB"))
        radioButton.initialize()
        radioButton
    }

    val textViewReg by lazy {
        val textView = BaseTextView()
        textView.textSize = 16F
        textView.textColor = Color.parseColor("#787880")
        textView.initialize()
        textView
    }

    init {

        renderUI()
    }

}

fun EventRegRadioButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        radioButtonReg,
        textViewReg
    )

    radioButtonReg
        .constrainTopToTopOf(constraintLayout,24)
        .constrainBottomToBottomOf(constraintLayout,24)
        .constrainLeftToLeftOf(constraintLayout,28)


    textViewReg
        .constrainLeftToRightOf(radioButtonReg,24)
        .constrainCenterYToCenterYOf(radioButtonReg)

}

fun EventRegRadioButtonViewHolder.initialize(text: String) {
    textViewReg.text = text
}
