package com.ageone.zenit.Modules.News.rows

import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import yummypets.com.stevia.constrainLeftToLeftOf
import yummypets.com.stevia.constrainTopToTopOf
import yummypets.com.stevia.subviews

class NewsItemViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {


    init {

        renderUI()
    }

}

fun NewsItemViewHolder.renderUI() {
    constraintLayout.subviews(

    )


}

fun NewsItemViewHolder.initialize() {

}
