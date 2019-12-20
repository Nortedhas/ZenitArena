package com.ageone.zenit.Modules.EventItem.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class EventImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewEventItem by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun EventImageViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewEventItem
    )

    imageViewEventItem
        .constrainTopToTopOf(constraintLayout, 24)
        .fillHorizontally(16)
        .height(utils.tools.getActualSizeFromDes(185))

}

fun EventImageViewHolder.initialize(image: Int) {
    addImageFromGlide(imageViewEventItem, image,8)
}
