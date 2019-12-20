package com.ageone.zenit.UIComponents.ViewHolders

import android.graphics.Color
import android.text.InputType
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.Button.BaseButton
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.*

class ButtonViewHolder(val constraintLayout: ConstraintLayout) : BaseViewHolder(constraintLayout) {

    val button by lazy {
        val button = BaseButton()
        button.backgroundColor = Color.parseColor("#00ACEB")
        button.textColor = Color.WHITE
        button.textSize = 20F
        button.cornerRadius = 6.dp
        button.inputType = InputType.TYPE_TEXT_VARIATION_NORMAL
        button.initialize()
        button
    }

    init {

        renderUI()
    }

}

fun ButtonViewHolder.renderUI() {
    constraintLayout.subviews(
        button
    )

    button
        .constrainTopToTopOf(constraintLayout,32)
        .fillHorizontally(16)
        .height(50)
}

fun ButtonViewHolder.initialize(text: String) {
    button.text = text
}
