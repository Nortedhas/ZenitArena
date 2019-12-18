package com.ageone.zenit.Modules.Auth.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class TextInputViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun TextInputViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun TextInputViewHolder.initialize() {

}
