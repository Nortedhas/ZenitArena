package com.ageone.zenit.Modules.Messages.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class MessagesChatViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun MessagesChatViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun MessagesChatViewHolder.initialize() {

}
