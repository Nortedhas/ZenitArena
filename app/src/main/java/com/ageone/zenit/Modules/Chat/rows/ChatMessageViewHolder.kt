package com.ageone.zenit.Modules.Chat.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class ChatMessageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun ChatMessageViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun ChatMessageViewHolder.initialize() {

}
