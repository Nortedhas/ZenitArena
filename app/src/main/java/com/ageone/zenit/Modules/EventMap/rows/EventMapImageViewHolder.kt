package com.ageone.zenit.Modules.EventMap.rows

import android.graphics.Color
import androidx.constraintlayout.widget.ConstraintLayout
import com.ageone.zenit.Application.utils
import com.ageone.zenit.External.Base.ImageView.BaseImageView
import com.ageone.zenit.External.Base.RecyclerView.BaseViewHolder
import com.ageone.zenit.External.Libraries.Glide.addImageFromGlide
import yummypets.com.stevia.*

class EventMapImageViewHolder(val constraintLayout: ConstraintLayout) :
    BaseViewHolder(constraintLayout) {

    val imageViewMap by lazy {
        val imageView = BaseImageView()
        imageView.backgroundColor = Color.TRANSPARENT
        imageView.initialize()
        imageView
    }

    init {

        renderUI()
    }

}

fun EventMapImageViewHolder.renderUI() {
    constraintLayout.subviews(
        imageViewMap
    )

    imageViewMap
        .constrainTopToTopOf(constraintLayout,16)
        .fillHorizontally(16)
        .height(utils.tools.getActualSizeFromDes(185))

}

fun EventMapImageViewHolder.initialize(image: Int) {
    addImageFromGlide(imageViewMap,image,8)
}
